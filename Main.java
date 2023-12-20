import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
       
        // Sample usage of the marketplace classes and methods
        Marketplace marketplace = new Marketplace();

        User user1 = new User("1", "John Doe", "john@example.com", "password");
        marketplace.createUser(user1);

        System.out.println(marketplace.loginUser("1","password"));

        Product product1 = new Product("p1", "Product 1", 10.0, 50);
        marketplace.getProducts().put(product1.getProductId(), product1);

        marketplace.addToCart("1", "p1", 3);
        List<CartItem> cart = marketplace.getCart("1");
        System.out.println("User Cart" + cart);

        marketplace.checkout("1", "WhiteHouse", "Gpay", "25/11/2023");
        List<Order> orderHistory = marketplace.getOrderHistory("1");
        System.out.println("Previous Orders" + orderHistory);
    }
}
