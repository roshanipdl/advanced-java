import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PriceCalculatorImpl extends UnicastRemoteObject implements PriceCalculator {
    
    public PriceCalculatorImpl() throws RemoteException {
        super();
    }
    
    @Override
    public double calculateSellingPrice(double costPrice, double discount) throws RemoteException {
        double sellingPrice = costPrice - discount;
        System.out.println("----------------------------------------------");
        System.out.println("[REQUEST RECEIVED]");
        System.out.println("Cost Price: Rs. " + costPrice);
        System.out.println("Discount: Rs. " + discount);
        System.out.println("Calculated Selling Price: Rs. " + sellingPrice);
        System.out.println("----------------------------------------------");
        return sellingPrice;
    }
} 