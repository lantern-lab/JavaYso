import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class TestCC6 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("cc6.out"));
        objectInputStream.readObject();
    }
}
