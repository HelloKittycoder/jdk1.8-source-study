package kittycoder.java.lang;

import org.junit.Test;

/**
 * Created by shucheng on 2019-11-1 下午 17:39
 */
public class IntegerTest {

    @Test
    public void testConstructor1() {
        // 通过指定的int值创建一个Integer对象
        Integer a1 = new Integer(59);
        System.out.println(a1);

        // 通过指定的String值创建一个Integer对象
        Integer a2 = new Integer("60");
        System.out.println(a2);
    }

    // https://www.cnblogs.com/liang1101/p/6380313.html
    @Test
    public void testParseInt() {
        int a1 = Integer.parseInt("11");
        System.out.println(a1);
        // a2的转换会报错，因为10进制最大的整数是2147483647（2^31-1）
        // int a2 = Integer.parseInt("2147483648");
        // System.out.println(a2);
        int a3 = Integer.parseInt("111", 2);
        System.out.println(a3); // 7
        int a4 = Integer.parseInt("FF", 16);
        System.out.println(a4); // 255
    }
}
