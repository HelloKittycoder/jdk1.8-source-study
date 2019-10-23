package kittycoder.java.lang;

import org.junit.Test;

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
    }
    /** =========================构造器=========================== **/
}
