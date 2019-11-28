package kittycoder.java.lang.classtest;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by shucheng on 2019-11-28 下午 21:36
 * 测试Class.newInstance方法
 */
public class ClassTest4 {

    private ClassTest4() {
        System.out.println("调用ClassTest4");
    }

    /*
    // 说明：如果要运行单元测试，先把上面的private改成public
    @Test
    public void testNewInstance() throws Exception {
        Class c1 = Class.forName("java.lang.String");
        Object instance = c1.newInstance();
        System.out.println("instance class : " + instance.getClass());
    }*/

    /*
    * Class.newInstance() 可以调用调用者本身的私有构造器（e.g.1）（也就是类可以调自己的私有构造器，这个很明显嘛），
    * 但是不能调用别的类的私有构造器（e.g.2）
    * 一般我们用反射都是调用别的类的构造器，不太可能去调自己的构造器，也就是场景2居多
    *
    * 这里顺便总结下Class.newInstance和Constructor.newInstance的区别：
    * （https://segmentfault.com/a/1190000009174512）
    * 1. Class.newInstance只能反射无参构造器
    * Constructor.newInstance可以反射无参、有参构造器
    * 2. Class.newInstance只能反射其他类的可见构造器
    * Constructor.newInstance除了可见构造器以外，还能反射私有构造器
    * 3. 对于异常来说，二者都会通过构造器抛出异常，但是差异在于：
    * Class.newInstance()对于捕获或者未捕获的异常均由构造器抛出，也就是说不做处理直接抛出;
    * Constructor.newInstance()通常会把抛出的异常封装成InvocationTargetException抛出
    *
    * 基于以上的差异，很多框架都是使用构造器反射的方式来获取对象（如：Spring、Jackson等）
    */
    public static void main(String[] args) throws Exception {
        // e.g.1
        Class c2 = Class.forName("kittycoder.java.lang.classtest.ClassTest4");
        /*Object o2 = c2.newInstance();
        System.out.println(o2);*/

        /*
        // e.g.2
        Class c1 = Class.forName("kittycoder.java.lang.classtest.MyTestClass");
        Object o1 = c1.newInstance();
        System.out.println(o1);*/

        // e.g.3
        Class c3 = Class.forName("kittycoder.java.lang.classtest.MyTestClass2");
        try {
            System.out.println(c3.newInstance());
        } catch (Exception e) {
            System.out.println(e instanceof ArithmeticException);
        }

        Constructor ct3 = c3.getConstructor();
        try {
            System.out.println(ct3.newInstance());
        } catch (Exception e) {
            System.out.println(e instanceof InvocationTargetException);
        }
    }
}

class MyTestClass {
    private MyTestClass() {
        System.out.println("调用MyTestClass的私有无参构造方法");
    }
}

class MyTestClass2 {

    public MyTestClass2() {
        System.out.println("调用MyTestClass的public无参构造方法");
        System.out.println(1/0);
    }
}
