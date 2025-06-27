import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PriceServer {
    public static void main(String[] args) {
        try {
            // Create the remote object
            PriceCalculatorImpl priceCalculator = new PriceCalculatorImpl();
            
            // Create or get the registry
            Registry registry = LocateRegistry.createRegistry(1099);
            
            // Bind the remote object to the registry
            registry.rebind("PriceCalculator", priceCalculator);
            
            System.out.println("==============================================");
            System.out.println("   Price Calculator RMI Server is RUNNING!   ");
            System.out.println("==============================================");
            System.out.println("Service bound to registry as 'PriceCalculator'");
            System.out.println();
            System.out.println("[INFO] Waiting for client requests...");
            System.out.println("[HINT] Run the PriceClient to perform calculations.");
            System.out.println("----------------------------------------------");
            
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
} 