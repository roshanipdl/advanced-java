import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PriceClient {
    public static void main(String[] args) {
        try {
            // Get the registry
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            
            // Look up the remote object
            PriceCalculator priceCalculator = (PriceCalculator) registry.lookup("PriceCalculator");
            
            // Define the values
            double costPrice = 34000.0;
            double discount = 3000.0;
            
            // Calculate selling price
            double sellingPrice = priceCalculator.calculateSellingPrice(costPrice, discount);
            
            // Display results
            System.out.println("=== Price Calculation Results ===");
            System.out.println("Cost Price: Rs. " + costPrice);
            System.out.println("Discount: Rs. " + discount);
            System.out.println("Selling Price: Rs. " + sellingPrice);
            System.out.println("================================");
            
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
} 