package kittycoder.java.lang.classtest;

/**
 * Created by shucheng on 2019-11-21 下午 22:23
 */
public class ClassTest3 {

    public static void main(String[] args) throws ClassNotFoundException {
       /* Class<?> c1 = Class.forName("java.lang.Integer");
        System.out.println(c1);*/

        Class myClass = Class.forName("kittycoder.java.lang.classtest.ClassTest3");
        ClassLoader loader = myClass.getClassLoader();
        Class<?> c2 = Class.forName("java.lang.String", true, loader);
        System.out.println(c2);
    }
}
