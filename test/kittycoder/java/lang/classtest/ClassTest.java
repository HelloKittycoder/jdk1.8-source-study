package kittycoder.java.lang.classtest;

/**
 * Created by shucheng on 2019-11-21 上午 9:30
 * 创建Class对象
 * Class没有public构造器，那么如何能创建Class对象呢？
 */
public class ClassTest {
    static {
        System.out.println("静态代码块");
    }
    {
        System.out.println("普通代码块");
    }
    public ClassTest() {
        System.out.println("构造方法");
    }

    public static void main(String[] args) {
        // 方式1：类名.class（会触发static块）
        /*Class<?> clazz1 = ClassTest.class;
        System.out.println("-------------");*/
        // 方式2：Class.forName（会触发static块）
        /*try {
            Class<?> clazz2 = Class.forName("kittycoder.java.lang.classtest.ClassTest");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("-------------");*/
        // 方式3：通过已有对象获取Class对象（会触发static块，普通块，构造方法）
        // getClass是Object的实例方法，获得obj对象的运行时类
        Class<?> clazz3 = new ClassTest().getClass();
    }
}
