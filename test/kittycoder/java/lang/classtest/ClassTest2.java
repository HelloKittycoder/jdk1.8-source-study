package kittycoder.java.lang.classtest;

import org.junit.Test;

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
}
