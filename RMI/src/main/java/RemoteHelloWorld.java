import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteHelloWorld extends UnicastRemoteObject implements RMIDemo{
    protected RemoteHelloWorld() throws RemoteException {
        System.out.println("构造方法");
    }

    public String hello() throws RemoteException {
        System.out.println("hello方法被调用");
        return "hello,world";
    }
}
