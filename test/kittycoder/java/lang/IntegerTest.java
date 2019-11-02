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
}
