package kittycoder.java.lang.classtest;

import org.junit.Test;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by shucheng on 2019-11-21 下午 21:35
 */
public class ClassTest2 {

    @Test
    public void testToString() throws ClassNotFoundException {
        Class<?> c1 = Class.forName("java.lang.String");
        Class c2 = int.class;
        Class c3 = void.class;
        Class c4 = CharSequence.class;
        Class c5 = ClassTest2.class;
        System.out.println("Class represented by c1:" + c1.toString()); // class java.lang.String
        System.out.println("Class represented by c2:" + c2.toString()); // int
        System.out.println("Class represented by c3:" + c3.toString()); // void
        System.out.println("Class represented by c4:" + c4.toString()); // interface java.lang.CharSequence
        System.out.println("Class represented by c5:" + c5.toString()); // class kittycoder.java.lang.classtest.ClassTest2
    }

    @Test
    public void testToGenericString() throws ClassNotFoundException {
        Class<?> c1 = Class.forName("java.lang.String");
        Class c2 = int.class;
        Class c3 = void.class;
        Class c4 = CharSequence.class;
        Class c5 = ClassTest2.class;
        Class c6 = Resource.class;
        Class c7 = TimeUnit.class;
        Class c8 = Map.class;
        System.out.println("Class represented by c1:" + c1.toGenericString()); // public final class java.lang.String
        System.out.println("Class represented by c2:" + c2.toGenericString()); // int
        System.out.println("Class represented by c3:" + c3.toGenericString()); // void
        System.out.println("Class represented by c4:" + c4.toGenericString()); // public abstract interface java.lang.CharSequence
        System.out.println("Class represented by c5:" + c5.toGenericString()); // public class kittycoder.java.lang.classtest.ClassTest2
        System.out.println("Class represented by c6:" + c6.toGenericString()); // public abstract @interface javax.annotation.Resource
        System.out.println("Class represented by c7:" + c7.toGenericString()); // public abstract enum java.util.concurrent.TimeUnit
        System.out.println("Class represented by c8:" + c8.toGenericString()); // public abstract interface java.util.Map<K,V>
    }
}
