import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream=new ObjectInputStream(new FileInputStream("test.out"));
        inputStream.readObject();
    }
}
