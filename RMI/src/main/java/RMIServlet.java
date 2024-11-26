import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class RMIServlet {
    public static void main(String[] args) throws RemoteException {
        RMIDemo hello = new RemoteHelloWorld();//创建远程对象
        Registry registry = LocateRegistry.createRegistry(10999);//创建注册表
        registry.rebind("hello",hello);//将远程对象注册到注册表里面，并且设置值为hello
    }
}
