import java.util.List;

public class Order {
    private String orderId;
    private List<CartItem> cartItems;
    private String shippingAddress;
    private String paymentInformation;
    private String orderDate;

    public String getOrderId() {
      return orderId;
    }

    public void setOrderId(String orderId) {
      this.orderId = orderId;
    }

    public List<CartItem> getCartItems() {
      return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
      this.cartItems = cartItems;
    }

    public String getShippingAddress() {
      return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
      this.shippingAddress = shippingAddress;
    }

    public String getPaymentInformation() {
      return paymentInformation;
    }

    public void setPaymentInformation(String paymentInformation) {
      this.paymentInformation = paymentInformation;
    }

    public String getOrderDate() {
      return orderDate;
    }

    public void setOrderDate(String orderDate) {
      this.orderDate = orderDate;
    }

    // Constructor, getters, and setters
    public  Order(String orderId,List<CartItem> cartItems, String  shippingAddress, String paymentInformation,String  orderDate){
      this.orderId = orderId;
      this.cartItems = cartItems;
      this.shippingAddress = shippingAddress;
      this.paymentInformation = paymentInformation;
      this.orderDate = orderDate;
    }

    public String toString(){
    return orderId + " "+  shippingAddress +  " " +paymentInformation  + " " +orderDate;
    }

}
