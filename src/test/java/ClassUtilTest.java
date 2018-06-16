import com.sarakeal.util.ClassUtil;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

public class ClassUtilTest {

    @Test
    public void getClassLoaderTest() {
        ClassLoader classLoader = ClassUtil.getClassLoader();
        System.out.println(classLoader.getName());
    }

    @Test
    public void getClassSetTest() {
        Set<Class<?>> classSet = ClassUtil.getClassSet("web");
        for(Class<?> cls : classSet) {
            System.out.println(cls.getName());
        }
    }

}
