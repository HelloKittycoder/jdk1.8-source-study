package kittycoder.java.lang.classtest;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by shucheng on 2019-12-2 下午 20:27
 * 参考链接：https://juejin.im/post/5c6547ee5188252f3048262b
 */
public class ClassTest2A {

    /**
     * 获取该Class对象所表示的类（及其所有超类）或接口（及其所有超接口）中的所有public字段
     * @throws Exception
     */
    @Test
    public void testGetFields() throws Exception {
        Class c = Class.forName("kittycoder.java.lang.classtest.ClassTest2A$C");
        Field[] fields = c.getFields();
        System.out.println("Below are the fields of ClassTest2A$C class:");
        for (Field f : fields) {
            System.out.println(f);
        }
    }

    /**
     * 获取该Class对象所表示的类（及其所有超类）或接口（及其所有超接口）的所有public方法
     * @throws Exception
     */
    @Test
    public void testGetMethods() throws Exception {
        Class c = Class.forName("kittycoder.java.lang.classtest.ClassTest2A$C");
        Method[] methods = c.getMethods();
        System.out.println("Below are the methods of ClassTest2A$C class:");
        for (Method m : methods) {
            System.out.println(m);
        }

        Class c2 = Class.forName("java.util.List");
        Method[] methods2 = c2.getMethods();
        System.out.println("Below are the methods of java.util.List class:");
        for (Method m : methods2) {
            System.out.println(m);
        }
    }

    /**
     * 获取该Class对象所表示的类中的所有public构造器
     * @throws Exception
     */
    @Test
    public void testGetConstructors() throws Exception {
        Class c1 = Class.forName("java.lang.Boolean");
        Constructor[] constructors = c1.getConstructors();
        System.out.println("Below are the constructors of Boolean class:");
        for (Constructor c : constructors) {
            System.out.println(c);
        }
    }

    /**
     * 获取该Class对象所表示的类或接口中的指定public字段，
     * （1）如果找不到，则一直在其每一个直接超接口上递归查找，直接超接口按其声明顺序搜索
     * （2）如果（1）中找不到字段，且Class有超类，则在其超类上递归搜索
     * @throws Exception
     */
    @Test
    public void testGetField() throws Exception {
        Class c = Class.forName("kittycoder.java.lang.classtest.ClassTest2A$C");
        System.out.println(c.getField("aname_pub"));
        System.out.println(c.getField("cname_pub"));
    }

    /**
     * 获取该Class对象所表示的类或接口中的指定public方法，
     * （1）如果找不到，则一直在其超类上递归查找
     * （2）如果（1）中找不到任何方法，则在其超接口上递归查找
     * @throws Exception
     */
    @Test
    public void testGetMethod() throws Exception {
        Class c = Class.forName("kittycoder.java.lang.classtest.ClassTest2A$C");
        System.out.println(c.getMethod("amethod_pub", null));
        System.out.println(c.getMethod("amethod_pub", String.class));
        System.out.println(c.getMethod("cmethod_pub", null));
    }

    interface AInterface {
        String Aitf_str = "1";
    }

    interface BInterface extends AInterface {
        String Bitf_str = "1";
        String aname_pub = "aa";
    }

    class A {
        private String aname_pri;
        private String aage_pri;

        public String aname_pub;
        public String aage_pub;

        public void amethod_pub(String a) {}
        public void amethod_pub() {}
        private void amethod_pri() {}
    }

    class B extends A implements BInterface {
        private String bname_pri;
        private String bage_pri;

        public String bname_pub;
        public String bage_pub;

        public void bmethod_pub() {}
        private void bmethod_pri() {}
    }

    class C extends B {
        private String cname_pri;
        private String cage_pri;

        public String cname_pub;
        public String cage_pub;

        public void cmethod_pub() {}
        private void cmethod_pri() {}
    }
}