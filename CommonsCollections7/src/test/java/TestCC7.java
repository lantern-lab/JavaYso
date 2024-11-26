import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class TestCC7 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("cc7.out"));
        objectInputStream.readObject();
    }
}
