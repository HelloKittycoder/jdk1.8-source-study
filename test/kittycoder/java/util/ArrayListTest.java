package kittycoder.java.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shucheng on 2019-10-8 下午 23:34
 */
public class ArrayListTest {

    // 将指定元素添加到list末尾
    @Test
    public void add() {
        List<String> stringList = new ArrayList<>();
        stringList.add("11");
        stringList.add("22");
        stringList.add("33");
        System.out.println(stringList);
    }

    // 给指定位置添加index
    @Test
    public void addIndex() {
        List<String> stringList = new ArrayList<>();
        stringList.add("11");
        stringList.add("22");
        stringList.add("33");
        stringList.add(1, "444");
        System.out.println(stringList);
    }

    // 测试set和get方法
    @Test
    public void testSetGet() {
        List<String> stringList = new ArrayList<>();
        stringList.add("11");
        stringList.add("22");
        stringList.add("33");
        String oldValue = stringList.set(1, "set后的值");
        System.out.println("index为1的旧元素为：" + oldValue);
        System.out.println("index为1的新元素为：" + stringList.get(1));
        // System.out.println(stringList.get(-1));
    }
}