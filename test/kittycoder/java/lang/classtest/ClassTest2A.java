package kittycoder.java.lang.classtest;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by shucheng on 2019-12-2 下午 20:27
 * 参考链接：https://juejin.im/post/5c6547ee5188252f3048262b
 */
public class ClassTest2A {

    /**
     * 获取该Class对象所表示的类（及其所有超类）或接口（及其所有超类）中的所有public字段
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
     * 返回一个Method数组，其中包含所有该Class对象所表示的类（及其所有超类）或接口（及其所有超接口）中所有public方法
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

    interface AInterface {
        String Aitf_str = "1";
    }

    interface BInterface extends AInterface {
        String Bitf_str = "1";
    }

    class A {
        private String aname_pri;
        private String aage_pri;

        public String aname_pub;
        public String aage_pub;

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
