import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception {
       
        // Sample usage of the marketplace classes and methods
        Marketplace marketplace = new Marketplace();

        User user1 = new User("1", "John Doe", "john@example.com", "password");
        marketplace.createUser(user1);

        System.out.println(marketplace.loginUser("1","password"));

        Product product1 = new Product("p1", "Product 1", 10.0, 4);
        marketplace.getProducts().put(product1.getProductId(), product1);

     ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(() -> {
            // User 1 adds to cart and checks out
            try {
                marketplace.addToCart("1", "p1", 4);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("User 1 added to cart");

            marketplace.checkout("1","whitehouse","gpay","25/12/2023");
            System.out.println("User 1 checked out");
        });

        executorService.submit(() -> {
            // User 2 adds to cart and checks out
            try {
                marketplace.addToCart("2", "p1", 4);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("User 2 added to cart");

            marketplace.checkout("2","empirestate","gpay","25/12/2023");
            System.out.println("User 2 checked out");
        });

        // Shutdown the executor service after tasks are submitted
        executorService.shutdown();

        // Wait for the threads to finish
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Display the final order history for both users
        System.out.println("User 1 order history: " + marketplace.getOrderHistory("1"));
        System.out.println("User 2 order history: " + marketplace.getOrderHistory("2"));
    }
}