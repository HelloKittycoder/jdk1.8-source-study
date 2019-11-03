package kittycoder.java.lang;

import org.junit.Test;

/**
 * Created by shucheng on 2019-11-1 下午 21:54
 */
public class IntegerDemo {

    // https://blog.csdn.net/qq_27870421/article/details/86712800
    @Test
    public void test1() {
        System.out.println("=================构造函数start=================");
        // 通过指定的int值创建一个Integer对象
        Integer a1 = new Integer(59);
        // 通过指定的String值创建一个Integer对象
        Integer a2 = new Integer("60");
        System.out.println("=================构造函数end=================");
        System.out.println();

        System.out.println("=================普通方法start=================");
        // 将此对象转化为int
        int b1 = a1.intValue();
        // 将此对象转化为long
        long b2 = a1.longValue();
        // 将此对象转化为byte
        byte b3 = a1.byteValue();
        // 将此对象转化为short
        short b4 = a1.shortValue();
        // 将此对象转化为double
        double b5 = a1.doubleValue();
        // 将此对象转化为float
        float b6 = a1.floatValue();

        // 将此对象与指定的对象进行比较
        System.out.println(a1.equals(a2));
        // 用Integer数字比较两个对象
        System.out.println(a1.compareTo(a2));

        // 返回此对象的hashcode
        System.out.println(a1.hashCode());
        // 返回此对象的String表示
        System.out.println(a1.toString());
        System.out.println("=================普通方法end=================");
        System.out.println();

        System.out.println("=================静态方法start=================");
        // 将int转化为Integer
        System.out.println(Integer.valueOf(20));
        // 将String转化为Integer
        System.out.println(Integer.valueOf("20"));
        // 将radix进制的数s（用String表示）转换成10进制表示的Integer
        System.out.println(Integer.valueOf("101", 2)); // 5
        // 返回两个int值中的较大值，就像通过调用Math.max一样
        System.out.println(Integer.max(20, 30)); // 30
        // 返回两个int值中的较小值，就像通过调用Math.max一样
        System.out.println(Integer.min(20, 30)); // 20
        // 使用+运算符将两个整数相加
        System.out.println(Integer.sum(20, 30)); // 50
        System.out.println("**************进制转换**************");
        // 返回指定整数的字符串表示
        System.out.println(Integer.toString(10)); // 10
        // 返回指定整数的radix进制表示
        System.out.println(Integer.toString(10, 2)); // 1010
        // 将int转换成基数为2的无符号整数，返回字符串表示形式
        System.out.println(Integer.toBinaryString(9)); // 1001
        // 将int转换成基数为16的无符号整数，返回字符串表示形式
        System.out.println(Integer.toHexString(10)); // a
        // 将int转换成基数为8的无符号整数，返回字符串表示形式
        System.out.println(Integer.toOctalString(9)); // 11
        System.out.println("**************无符号整数相关**************");
        // 通过无符号转换将参数转为long
        System.out.println(Integer.toUnsignedLong(10));
        // 返回参数作为10进制无符号整数的字符串表示
        System.out.println(Integer.toUnsignedString(100));
        // 返回参数作为指定进制无符号整数的字符串表示
        System.out.println(Integer.toUnsignedString(3, 2)); // 11
        // 将两个整数视作无符号整数进行比较
        System.out.println("compareUnsigned" + Integer.compareUnsigned(10, 20));
        // 将String解码成Integer
        System.out.println(Integer.decode("#FF"));
        // 返回将第一个参数除以第二个参数的无符号商，其中每个参数和结果都被解释为无符号值
        System.out.println(Integer.divideUnsigned(10, 3));

        // 将字符串解析成带符号的十进制整数
        System.out.println(Integer.parseInt("-20"));
        // 将radix进制整数的字符串表示解析成带符号的十进制整数
        System.out.println(Integer.parseInt("111", 2)); // 7
        // 将字符串解析成无符号的十进制整数
        System.out.println(Integer.parseUnsignedInt("20")); // 20
        // 将radix进制整数的字符串表示解析成无符号的十进制整数
        System.out.println(Integer.parseUnsignedInt("111", 2)); // 7
        // 返回将第一个参数除以第二个参数的无符号余数，其中每个参数和结果都被解释为无符号值
        System.out.println(Integer.remainderUnsigned(10, 3)); // 1

        System.out.println("**************位运算**************");
        // 计算一个int，long类型的数值在二进制下“1”的数量
        // https://blog.csdn.net/u011497638/article/details/77947324
        System.out.println(Integer.bitCount(3));
        // 取i这个数的二进制形式中处于最低位的1，剩下高位全部补0
        // 9的二进制是1001，截出来的结果就是最右边的1
        System.out.println("lowestOneBit==" + Integer.lowestOneBit(9)); // 1
        // 取i这个数的二进制形式中处于最高位的1，剩下低位全部补0
        System.out.println("highestOneBit==" + Integer.highestOneBit(10)); // 8
        // 返回整数i的最高非零位前面的0的个数
        System.out.println("numberOfLeadingZeros==" + Integer.numberOfLeadingZeros(-9)); // 28
        // 返回整数i的最低非零位后面的0的个数
        System.out.println(Integer.numberOfTrailingZeros(9)); // 0

        // 将整数i的二进制表示倒序排列
        System.out.println(Integer.reverse(11)); // -805306368
        // http://blog.sina.com.cn/s/blog_8a7a0d5501015fpc.html
        // 将第一个字节与第四个字节的位置互换，第二个字节与第三个字节位置互换
        // 举例：Integer.reverseBytes(1025)=17039360
        // 对比1025和17039360的二进制表示
        // 0000 0000|0000 0000|0000 0100|0000 0001
        // 0000 0001|0000 0100|0000 0000|0000 0000
        System.out.println("reverseBytes=========" + Integer.reverseBytes(5)); // 184549376

        // https://blog.csdn.net/zuoyouzouzou/article/details/88892198
        // 循环左移，将整数i左移distance，如果distance为负，则右移-distance
        System.out.println(Integer.rotateLeft(11, 2)); // 44
        // 循环右移，将整数i无符号右移distance，如果distance为负，则左移-distance
        // 举例：11的二进制表示，与循环右移2位的二进制表示如下
        // 0000 0000|0000 0000|0000 0100|0000 1011
        // 1100 0000|0000 0000|0000 0100|0000 1000
        System.out.println(Integer.rotateRight(11, 2)); // -1073741822

        System.out.println("**************其他**************");
        // 返回指定整数的signum函数值
        System.out.println(Integer.signum(-5)); // -1
        // 根据指定的系统属性名称获取Integer
        System.out.println(Integer.getInteger("idea.test.cyclic.buffer.size"));
        System.out.println(Integer.getInteger("aa"));
        // 根据指定的系统属性名称获取Integer，如果没有则返回val
        System.out.println(Integer.getInteger("aa", 11));

        System.out.println("=================静态方法end=================");
    }

    // https://www.cnblogs.com/ysocean/p/8075676.html
    @Test
    public void test2() {
        Integer i01 = 59;
        int i02 = 59;
        Integer i03 = Integer.valueOf(59);
        Integer i04 = new Integer(59);
        System.out.println(i01 == i02); // true
        System.out.println(i01 == i03); // true
        System.out.println(i03 == i04); // false
        System.out.println(i02 == i04); // true
    }
}
