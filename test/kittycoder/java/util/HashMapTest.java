package kittycoder.java.util;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by shucheng on 2019-10-19 下午 20:44
 * https://gitee.com/SnailClimb/JavaGuide/blob/master/docs/java/collection/HashMap.md
 * https://www.cnblogs.com/gjmhome/p/11396728.html
 *
 * 后续有空再回头细看
 */
public class HashMapTest {

    @Test
    public void testPut() {
        HashMap<String, String> map = new HashMap<>();
        map.put("1", "张1");
        map.put("2", "张2");
        map.put("3", "张3");
        map.put("4", "张4");
        map.put("5", "张5");
        map.put("6", "张6");
        map.put("7", "张7");
        map.put("8", "张8");
        map.put("9", "张9");
        map.put("10", "张10");
        map.put("11", "张11");
        map.put("12", "张12");
        System.out.println(map);
    }

    // 测试hash方法，该方法用来重新计算对象的hash值
    @Test
    public void testHash() throws Exception {
        Method hash = HashMap.class.getDeclaredMethod("hash", Object.class);
        hash.setAccessible(true);
        System.out.println(hash.invoke(null, "1"));
        System.out.println(hash.invoke(null, "2"));
        System.out.println(hash.invoke(null, "3"));
        System.out.println(hash.invoke(null, "4"));
        System.out.println(hash.invoke(null, "11"));
        System.out.println(hash.invoke(null, "12"));
        int a = (int)(Math.pow(-2, 31));
        System.out.println(Integer.toBinaryString(a).length());
    }

    // 测试tableSizeFor方法，该方法用来计算数组的容量
    @Test
    public void testTableSizeFor() throws Exception {
        Method tableSizeFor = HashMap.class.getDeclaredMethod("tableSizeFor", int.class);
        tableSizeFor.setAccessible(true);
        System.out.println(tableSizeFor.invoke(null, 0));
        System.out.println(tableSizeFor.invoke(null, 1));
        System.out.println(tableSizeFor.invoke(null, 2));
        System.out.println(tableSizeFor.invoke(null, 3));
        System.out.println(tableSizeFor.invoke(null, 5));
        System.out.println(tableSizeFor.invoke(null, 9));
        System.out.println(tableSizeFor.invoke(null, 17));
    }

    @Test
    public void test() {
        int[] arr = new int[]{2,2,1};
        int num = arr[0];
        for (int i = 1; i < arr.length; i++) {
            num ^= arr[i];
        }
        System.out.println(num);
    }
}
