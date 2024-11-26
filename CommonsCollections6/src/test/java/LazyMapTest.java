import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class LazyMapTest {
    public static void main(String[] args) {
        final AtomicInteger i = new AtomicInteger();
        Transformer Testtransformer = new Transformer() {
            public Object transform(Object o) {
                return "第" + i.incrementAndGet() + "次看我";
            }
        };
        Map map=new HashMap();
        Map lazyMap= LazyMap.decorate(map,Testtransformer);
        TiedMapEntry tiedMapEntry=new TiedMapEntry(lazyMap,"cc6");
        System.out.println(map);
        System.out.println(lazyMap);
    }
}
