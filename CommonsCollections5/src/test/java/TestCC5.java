import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class TestCC5 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream=new ObjectInputStream(new FileInputStream("cc5.out"));
        inputStream.readObject();
    }
}
