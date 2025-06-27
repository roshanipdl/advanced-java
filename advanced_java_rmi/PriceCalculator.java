import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PriceCalculator extends Remote {
    double calculateSellingPrice(double costPrice, double discount) throws RemoteException;
} 