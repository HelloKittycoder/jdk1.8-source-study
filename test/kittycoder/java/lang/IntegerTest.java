package kittycoder.java.lang;

import org.junit.Test;

import java.lang.reflect.Field;

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

    @Test
    public void testValueOf() {
        System.out.println(sun.misc.VM.getSavedProperty("java.lang.Integer.IntegerCache.high"));
        int a1 = Integer.valueOf(20);
        System.out.println(a1);
        int a2 = Integer.valueOf("20");
        System.out.println(a2);
        int a3 = Integer.valueOf("101", 2); // 5
        System.out.println(a3);
    }

    // https://segmentfault.com/a/1190000007002125
    @Test
    public void testToString() {
        Integer a1 = new Integer(20);
        // 返回此对象的String表示
        System.out.println(a1.toString());

        // 返回指定整数的字符串表示
        String s1 = Integer.toString(10);
        System.out.println(s1); // 10
    }

    // ===============Integer.toString()相关细节问题测试start===============
    // 测试Integer#getChars方法里为什么用52429这个数字？
    // https://www.zhihu.com/question/34948884/answer/60497785
    @Test
    public void test1() {
        System.out.println(Math.log10(52429*65536.0)/Math.log10(2)); // 32（右移19）
        System.out.println(Math.log10(104858*65536.0)/Math.log10(2)); // 33（右移20）
        System.out.println(Math.log10(209715*65536.0)/Math.log10(2)); // 34（右移21）
    }

    // 测试getChars方法里用到的DigitTens，DigitOnes变量
    @Test
    public void test2() throws Exception {
        Field digitTensField = Integer.class.getDeclaredField("DigitTens");
        Field digitOnesField = Integer.class.getDeclaredField("DigitOnes");
        digitTensField.setAccessible(true);
        digitOnesField.setAccessible(true);
        char[] digitTens = (char[]) digitTensField.get(null);
        char[] digitOnes = (char[]) digitOnesField.get(null);

        System.out.println(digitTens[25] + "" +  digitOnes[25]);
        System.out.println(digitTens[48] + "" +  digitOnes[48]);
        System.out.println(digitTens[37] + "" +  digitOnes[37]);
    }

    // Integer.MIN_VALUE由于溢出的原因，无法取相反数
    @Test
    public void test3() {
        int i = Integer.MIN_VALUE;
        System.out.println(i); // -2147483648
        i = -i;
        System.out.println(i); // -2147483648
    }
    // ===============Integer.toString()相关细节问题测试end===============

    @Test
    public void testToString2() {
        // 返回指定整数的radix进制表示
        String s1 = Integer.toString(10, 2);
        System.out.println(s1); // 1010
        String s2 = Integer.toString(-10, 2);
        System.out.println(s2); // -1010
    }
}
