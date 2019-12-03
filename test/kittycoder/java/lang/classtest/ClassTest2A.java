package kittycoder.java.lang.classtest;

import org.junit.Test;

import java.lang.reflect.Field;

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
    }

    class B extends A implements BInterface {
        private String bname_pri;
        private String bage_pri;

        public String bname_pub;
        public String bage_pub;
    }

    class C extends B {
        private String cname_pri;
        private String cage_pri;

        public String cname_pub;
        public String cage_pub;
    }
}
