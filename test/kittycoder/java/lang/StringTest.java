package kittycoder.java.lang;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shucheng on 2019-10-23 上午 8:51
 */
public class StringTest {

    // 测试compareToIgnoreCase方法
    @Test
    public void testCompareToIgnoreCase() {
        System.out.println("AB".compareToIgnoreCase("ab")); // 0
        System.out.println("AB".compareToIgnoreCase("ac")); // -1
        System.out.println("AC".compareToIgnoreCase("ab")); // 1
        System.out.println("I".compareToIgnoreCase("İ")); // 0
    }

    // http://forums.codeguru.com/showthread.php?207356-Question-about-String-compareToIgnoreCase
    // 只需要找出char里有不同的大写，但是有相同小写的就行了
    @Test
    public void testComparator() {
        int CHAR_TEST = 1000;
        for (int i = 0; i < CHAR_TEST; i++) {
            char ch = (char) i;
            /*for (int j = 0; j < CHAR_TEST; j++) {*/
            for (int j = i; j < CHAR_TEST; j++) {
                if (i != j) {
                    char ch2 = (char) j;
                    if (ch2 != ch) {
                        boolean b1 = Character.toUpperCase(ch) == Character.toUpperCase(ch2);
                        boolean b2 = Character.toLowerCase(ch) == Character.toLowerCase(ch2);
                        /*if (b1 && !b2) {
                            System.out.println("UPPER SAME FOR: " + i + " and " + j + ".");
                            System.out.println("Ch1=" + ch + ".Ch2=" + ch2 + ".");
                        } else {
                            if (b2 && !b1) {
                                System.out.println("LOWER SAME FOR: " + i + " and " + j + ".");
                                System.out.println("Ch1=" + ch + ".Ch2=" + ch2 + ".");
                            }
                        }*/
                        if (b2 && !b1) {
                            System.out.println("LOWER SAME FOR: " + i + " and " + j + ".");
                            System.out.println("Ch1=" + ch + ".Ch2=" + ch2 + ".");
                        }
                    }
                }
            }
        }
    }

    /** =========================构造器=========================== **/
    @Test
    public void testConstructor1() {
        // 通过已有的String来创建一个String对象
        String str = new String("test");
        System.out.println(str);

        // 通过char数组来创建一个String对象
        char[] ch1 = {'T', 'E', 'S', 'T'};
        str = new String(ch1);
        System.out.println(str);

        // 截取char数组中的某一部分来创建一个String对象
        str = new String(ch1, 1, 2);
        System.out.println(str);
    }

    // 测试下Note: offset or count might be near -1>>>1.
    // 这句话是在说溢出问题：
    // 意思是说要注意offset或count都有可能接近 -1>>>1
    // 需要写成：offset > value.length - count
    // 而不能写成：offset + count > length
    // http://www.hackerav.com/?post=25743
    @Test
    public void testOneNoteInStringOffsetCount() {

        // int占32位，-1用二进制表示是32个1，无符号右移一位后，
        // 高位补0，也就是0后面跟31个1，变成了2^31（就是Integer.MAX_VALUE）
        // System.out.println(-1>>>1 == Integer.MAX_VALUE); // true

        int cc = -1>>>1;
        int offset = cc - 1;
        int length = cc;
        int count = 3;
        System.out.println(offset > length - count);
        System.out.println(offset + count > length);
    }

    @Test
    public void testConstructor2() throws Exception {
        // 通过byte数组来创建String
        byte[] abyte = {72, 101, 108, 108, 111};
        String str = new String(abyte);
        System.out.println(str); // Hello

        // 截取byte数组中的某一部分来创建String
        str = new String(abyte, 1, 2);
        System.out.println(str); // el

        // 通过byte数组来创建String，同时指定charsetName
        str = new String(abyte, "UTF-8");
        System.out.println(str);

        // 截取byte数组中的某一部分来创建String，同时指定charsetName
        str = new String(abyte, 1, 2, "UTF-8");
        System.out.println(str); // el
    }

    @Test
    public void testConstructor3() throws Exception {
        // 通过StringBuffer来创建String
        StringBuilder sbuilder = new StringBuilder("111");
        String str = new String(sbuilder);
        System.out.println(str);

        // 通过StringBuilder来创建String
        StringBuffer sbuffer = new StringBuffer("222");
        str = new String(sbuffer);
        System.out.println(str);
    }
    /** =========================构造器=========================== **/

    @Test
    public void test1() {
        String str = "ABCabc";
        System.out.println(str.length());
        System.out.println(str.isEmpty());
        System.out.println(str.charAt(1));
        System.out.println(str.codePointAt(2));
    }

    @Test
    public void test2() {
        String str = "ABCabc321";
        System.out.println(str + "是以A开头：" + str.startsWith("A")); // true
        System.out.println(str + "是以1结尾：" + str.endsWith("1")); // true
    }

    @Test
    public void test3() {
        String str = "ABC123abc321";
        System.out.println(str + "中B第一次出现的位置的索引为：" + str.indexOf('B')); // str.indexOf(66) 1
        System.out.println(str + "中2最后一次出现的位置的索引为：" + str.lastIndexOf('2')); // str.lastIndexOf(50) 10
    }

    @Test
    public void test4() {
        String str = "ABC123abc321";
        System.out.println(str.indexOf("", 5)); // 0 （targetCount==0）
        System.out.println(str.indexOf("", 12)); // 12 （fromIndex>=sourceCount）
        System.out.println(str.indexOf("BC")); // 1
        System.out.println(str.lastIndexOf("32")); // 9 lastIndexOf暂时没看懂，后面有时间再看
    }

    @Test
    public void test5() {
        String str = "ABC123abc321";
        System.out.println("截取" + str + "中索引大于等于3的所有字符：" + str.substring(3));
        System.out.println("截取" + str + "中索引大于等于3小于6的所有字符：" + str.substring(3, 6));
    }

    @Test
    public void testContains() {
        String str = "ABC123abc321";
        System.out.println(str + "中包含AB：" + str.contains("AB"));
    }

    @Test
    public void testConcat() {
        String str1 = "test1";
        String str2 = "test2";
        System.out.println(str1 + "和" + str2 + "拼接后的结果为：" + str1.concat(str2));
    }

    @Test
    public void testJoin() {
        String[] strArr = {"aa", "bb", "cc"};
        System.out.println("将strArr用|拼接：" + String.join("|", strArr));
        List<String> strList = new ArrayList<>();
        strList.add("s1");
        strList.add("s2");
        strList.add("s3");
        System.out.println("将strList用|拼接：" + String.join("|", strList));
    }

    @Test
    public void testReplece() {
        String str = "teset";
        System.out.println(str.replace('t', 'h')); // heseh（要替换的char在索引为0的位置）
        System.out.println(str.replace('k', 'h')); // teset（找不到要替换的char）
        System.out.println(str.replace('e', 'h')); // thsht（要替换的char在索引>0的位置）
    }
}
