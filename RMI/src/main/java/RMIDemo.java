import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIDemo extends Remote {
    public String hello() throws RemoteException;
}
