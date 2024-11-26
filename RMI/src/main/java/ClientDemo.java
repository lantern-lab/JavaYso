import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientDemo {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost", 10999);//获取远程主机对象
        // 利用注册表的代理去查询远程注册表中名为hello的对象
        RMIDemo hello = (RMIDemo) registry.lookup("hello");
        // 调用远程方法
        System.out.println(hello.hello());
    }
}
