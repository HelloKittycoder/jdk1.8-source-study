package kittycoder.java.lang.classtest;

import org.junit.Test;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.lang.reflect.TypeVariable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by shucheng on 2019-11-21 下午 21:35
 * 参考链接：https://juejin.im/post/5c6547ee5188252f3048262b
 * https://blog.csdn.net/qq_35029061/article/details/100550584
 * https://blog.csdn.net/a327369238/article/details/52577040
 * https://www.jianshu.com/p/4ae6267caee5
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

    // 判断指定对象是否为当前类的实例
    @Test
    public void testIsInstance() throws Exception {
        Class c = Class.forName("java.lang.String");
        String url = "http://www.baidu.com";
        boolean b1 = c.isInstance(url);
        System.out.println("is url instance of String:" + b1); // true

        int i = 10;
        boolean b2 = c.isInstance(i);
        System.out.println("is i instance of String:" + b2); // false

        System.out.println("aaa" instanceof String); // true
        System.out.println(String.class.isInstance("aaa")); // true
    }

    // 判断当前类是否为指定对象所表示的类或接口的父类或父接口
    @Test
    public void testIsAssignableFrom() throws Exception {
        // c1是c2的父接口（c1是接口，c2是类）
        Class c1 = Class.forName("java.lang.CharSequence");
        Class c2 = Class.forName("java.lang.String");
        System.out.println(c1.isAssignableFrom(c2)); // true

        // c3是c4的父接口（c3、c4都是接口）
        Class c3 = Class.forName("java.util.Collection");
        Class c4 = Class.forName("java.util.List");
        System.out.println(c3.isAssignableFrom(c4)); // true

        // c5是c6的父类（c5是抽象类，c6是类）
        Class c5 = Class.forName("java.util.AbstractList");
        Class c6 = Class.forName("java.util.ArrayList");
        System.out.println(c5.isAssignableFrom(c6)); // true
    }

    // 判断指定的Class对象是否表示接口类型
    @Test
    public void testIsInterface() throws Exception {
        Class c1 = Class.forName("java.lang.String"); // 普通类
        System.out.println("is java.lang.String an interface:" + c1.isInterface()); // false

        Class c2 = Class.forName("java.lang.Runnable"); // 接口
        System.out.println("is java.lang.Runnable an interface:" + c2.isInterface()); // true

        Class c3 = Class.forName("java.util.AbstractList"); // 抽象类
        System.out.println("is java.util.AbstractList an interface:" + c3.isInterface()); // false
    }

    // 判断指定的Class对象是否表示基本类型
    @Test
    public void testIsPrimitive() {
        System.out.println(boolean.class.isPrimitive()); // true
        System.out.println(Boolean.class.isPrimitive()); // false

        Class c = char.class;
        System.out.println("is " + c.toString() + " primitive:" + char.class.isPrimitive()); // true
        System.out.println(byte.class.isPrimitive()); // true
        System.out.println(short.class.isPrimitive()); // true

        System.out.println(int.class.isPrimitive()); // true
        System.out.println(Integer.class.isPrimitive()); // false

        System.out.println(long.class.isPrimitive()); // true
        System.out.println(float.class.isPrimitive()); // true
        System.out.println(double.class.isPrimitive()); // true

        System.out.println(void.class.isPrimitive()); // true
    }

    // 判断指定的Class对象是否表示数组类
    @Test
    public void testIsArray() {
        int[] a = new int[2];
        ClassTest2[] b = new ClassTest2[1];

        Class c1 = a.getClass();
        Class c2 = ClassTest2.class;
        Class c3 = b.getClass();

        boolean b1 = c1.isArray();
        boolean b2 = c2.isArray();
        boolean b3 = c3.isArray();

        System.out.println("is " + c1.toString() + " an array:" + b1); // true
        System.out.println("is " + c2.toString() + " an array:" + b2); // false
        System.out.println("is " + c3.toString() + " an array:" + b3); // true
    }

    // 判断指定的Class对象是否为匿名类
    @Test
    public void testIsAnonymousClass() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("111");
            }
        };
        System.out.println(r.getClass().isAnonymousClass()); // true
    }

    // 判断指定的Class对象是否为局部类
    @Test
    public void testIsLocalClass() {
        class AClass {
            private int a;
            @Override
            public String toString() {
                return "a的值为：" + a;
            }
        }

        AClass aClass = new AClass();
        aClass.a = 1;
        System.out.println(aClass);
        System.out.println(AClass.class.isLocalClass()); // true
        System.out.println(AClass.class.isMemberClass()); // false
    }

    // 判断指定的Class对象是否为成员类（其实就是看这个class对应的类是不是某个类的属性）
    @Test
    public void testIsMemberClass() {
        System.out.println(TestClassA.class.isMemberClass()); // true
        System.out.println(TestClassB.class.isMemberClass()); // true
        System.out.println(String.class.isMemberClass()); // false
    }

    // 判断指定的Class对象是否为枚举（即声明为enum的）
    @Test
    public void testIsEnum() {
        System.out.println(TimeUnit.class.isEnum()); // true
        System.out.println(TestClassA.class.isEnum()); // false
    }

    // 判断指定的Class对象是否为注释类型
    @Test
    public void testIsAnnotation() {
        System.out.println(B.class.isAnnotation()); // true
        System.out.println(B.class.isInterface()); // true
        System.out.println(TestClassA.class.isAnnotation()); // false
    }

    // 判断该类是否为合成类
    // 参考链接：https://www.javatpoint.com/java-class-issynthetic-method
    @Test
    public void testIsSynthetic() {
        Runnable r = () -> System.out.println("111");
        System.out.println(r.getClass().isSynthetic()); // true
    }

    // 获取类的名称（含包名）
    // toString里调用的就是getName方法
    @Test
    public void testGetName() {
        System.out.println(int.class.getName()); // int
        System.out.println(int[].class.getName()); // [I
        System.out.println(String.class.getName()); // java.lang.String
        System.out.println(TestClassA.class.getName()); // kittycoder.java.lang.classtest.ClassTest2$TestClassA
        System.out.println(B.class.getName()); // kittycoder.java.lang.classtest.ClassTest2$B

        // 匿名类
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("111");
            }
        };
        System.out.println(r.getClass().getName());

        // 匿名类数组
        Runnable[] rArr = (Runnable[]) Array.newInstance(r.getClass(), 2);
        System.out.println(rArr.getClass().getName());
    }

    // 获取类的名称（不含包名）
    @Test
    public void testGetSimpleName() {
        System.out.println(int.class.getSimpleName()); // int
        System.out.println(int[].class.getSimpleName()); // int[]
        System.out.println(String.class.getSimpleName()); // String
        System.out.println(TestClassA.class.getSimpleName()); // TestClassA
        System.out.println(B.class.getSimpleName()); // B

        // 匿名类
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("111");
            }
        };
        System.out.println("".equals(r.getClass().getSimpleName()));

        // 匿名类数组
        Runnable[] rArr = (Runnable[]) Array.newInstance(r.getClass(), 2);
        System.out.println(rArr.getClass().getSimpleName());
    }

    // 获取此类的类加载器
    @Test
    public void testGetClassLoader() throws Exception {
        Class c = Class.forName("kittycoder.java.lang.classtest.ClassTest2");
        System.out.println(c.getClassLoader()); // AppClassLoader
        System.out.println(String.class.getClassLoader()); // null
        System.out.println(int.class.getClassLoader()); // null
    }

    // 获取类的泛型声明的类型变量（如果没有泛型声明，返回一个空数组）
    @Test
    public void testGetTypeParameters() throws Exception {
        Class c = Class.forName("java.util.List");
        TypeVariable[] typeParameters = c.getTypeParameters();
        System.out.println("TypeVariables in " + c.getName() + " class:");
        // 打印E
        for (TypeVariable typeVariable : typeParameters) {
            System.out.println(typeVariable.getName());
        }

        Class c2 = Class.forName("java.util.Map");
        TypeVariable[] typeParameters2 = c2.getTypeParameters();
        System.out.println("TypeVariables in " + c2.getName() + " class:");
        // 依次打印K，V
        for (TypeVariable typeVariable : typeParameters2) {
            System.out.println(typeVariable.getName());
        }
        System.out.println(c2.getTypeParameters()); // 验证genericInfo是否为懒加载

        Class c3 = Class.forName("kittycoder.java.lang.classtest.ClassTest2");
        TypeVariable[] typeParameters3 = c3.getTypeParameters(); // 得到一个长度为0的TypeVariable数组
        System.out.println(typeParameters3.length);
    }

    class TestClassA {}
    static class TestClassB {}
    @interface B {}
}
