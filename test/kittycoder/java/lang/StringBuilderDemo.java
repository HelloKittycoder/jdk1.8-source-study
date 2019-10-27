package kittycoder.java.lang;

import org.junit.Test;

/**
 * Created by shucheng on 2019-10-27 下午 17:07
 * https://docs.oracle.com/javase/8/docs/api/java/lang/StringBuilder.html
 */
public class StringBuilderDemo {

    @Test
    public void test1() {
        StringBuilder sbuilder = new StringBuilder();
        System.out.println("默认容量为：" + sbuilder.capacity());
        System.out.println("======================================================");
        // append方法
        sbuilder.append('A');
        System.out.println(sbuilder.toString()); // A
        sbuilder.append(1);
        System.out.println(sbuilder.toString()); // A1
        sbuilder.append(2.5f);
        System.out.println(sbuilder.toString()); // A12.5
        sbuilder.append(true);
        System.out.println(sbuilder.toString()); // A12.5true
        sbuilder.append("张三");
        System.out.println(sbuilder.toString()); // A12.5true张三

        System.out.println("======================================================");
        // delete方法
        sbuilder.delete(0, 3);
        System.out.println(sbuilder); // .5true张三
        sbuilder.deleteCharAt(0);
        System.out.println(sbuilder); // 5true张三

        System.out.println("======================================================");
        // insert方法
        sbuilder.insert(0, "A12.");
        System.out.println(sbuilder); // A12.5true张三

        System.out.println("======================================================");
        // replace方法
        sbuilder.replace(1, 3, "he");
        System.out.println(sbuilder); // Ahe.5true张三

        System.out.println("======================================================");
        // reverse方法
        sbuilder.reverse();
        System.out.println(sbuilder); // 三张eurt5.ehA
    }
}
