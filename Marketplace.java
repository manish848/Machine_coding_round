import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Marketplace {
    private Map<String, User> users = new HashMap<>();
    private Map<String, Product> products = new HashMap<>();
    private Map<String, List<CartItem>> userCarts = new HashMap<>();
    private Map<String, List<Order>> userOrderHistory = new HashMap<>();

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

    public Map<String, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Product> products) {
        this.products = products;
    }

    public Map<String, List<CartItem>> getUserCarts() {
        return userCarts;
    }

    public void setUserCarts(Map<String, List<CartItem>> userCarts) {
        this.userCarts = userCarts;
    }

    public Map<String, List<Order>> getUserOrderHistory() {
        return userOrderHistory;
    }

    public void setUserOrderHistory(Map<String, List<Order>> userOrderHistory) {
        this.userOrderHistory = userOrderHistory;
    }

    public void createUser(User user) {

        users.put(user.getUserId(), user);
    }

    public String loginUser(String userId, String password) {
        if(users.containsKey(userId))
        {
            if(users.get(userId).getPassword().equals(password))
            return "login Successfull";
            else
            return "Password Incorrect";
        }
        return "User Does Not Exist";
    }

    public User getUser(String userId) {
        return users.get(userId);
    }

    public Product getProduct(String productId) {
        // Return product details or handle non-existence
        return products.get(productId);
    }

    public void addToCart(String userId, String productId, int quantity) throws Exception {

        //if either of productId or its quantity is not possible
        if(getProduct(productId)==null || quantity > getProduct(productId).getQuantityInStock()){
            throw new Exception("invalid operation");
        }
        // Logic to add/update product in the user's cart
        List<CartItem> cartOfUser = userCarts.getOrDefault(userId,new ArrayList<CartItem>());
        boolean flag = false;
        for(CartItem cartItem : cartOfUser){
            if(cartItem.getProduct().getProductId().equals(productId)){
                cartItem.setQuantity(quantity);
                flag = true;
            }
        }
        if(!flag){
            cartOfUser.add(new CartItem(getProduct(productId), quantity));
            userCarts.put(userId,cartOfUser);
            // userCarts.getOrDefault(userId,new ArrayList<>()).add(new CartItem(getProduct(productId), quantity));
        }



    }

    public List<CartItem> getCart(String userId) {
        // Return the user's shopping cart or handle non-existence
        return userCarts.getOrDefault(userId, new ArrayList<>());
    }

    public synchronized void checkout(String userId, String shippingAddress, String paymentInformation, String orderDate) {
    List<CartItem> cart = userCarts.getOrDefault(userId, new ArrayList<>());
    if (cart.isEmpty()) {
        // Handle empty cart
        return;
    }

    // Check if there is sufficient stock for each product in the cart
    for (CartItem cartItem : cart) {
        Product product = cartItem.getProduct();
        int quantityInCart = cartItem.getQuantity();

        // Check if there is enough quantity in stock
        if (product.getQuantityInStock() < quantityInCart) {
            // Handle insufficient stock
            return;
        }
    }
    String orderId = userId + userOrderHistory.getOrDefault(userId, new ArrayList<>()).size();
    List<CartItem> cartItems = userCarts.get(userId);

    // Create a new order and update inventory

    Order order = new Order(orderId, cartItems,shippingAddress, paymentInformation, orderDate);
    for (CartItem cartItem : cart) {
        Product product = cartItem.getProduct();
        int quantityInCart = cartItem.getQuantity();
        product.setQuantityInStock(product.getQuantityInStock() - quantityInCart);
    }


    // cart.clear();
    userOrderHistory.computeIfAbsent(userId, k -> new ArrayList<>()).add(order);
}

    public List<Order> getOrderHistory(String userId) {
        return userOrderHistory.getOrDefault(userId,new ArrayList<>());
    }
}

