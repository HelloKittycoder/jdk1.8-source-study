package kittycoder.java.util;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by shucheng on 2019-10-18 上午 8:36
 */
public class LinkedListTest {

    // 将指定元素添加到链表末尾
    @Test
    public void testAdd() {
        List<String> strList = new LinkedList<>();
        strList.add("11");
        strList.add("22");
        strList.add("33");
        System.out.println(strList);
    }

    // 给指定位置添加元素
    @Test
    public void testAddIndex() {
        List<String> strList = new LinkedList<>();
        strList.add("11");
        strList.add("22");
        strList.add("33");
        strList.add(1, "hehe");
        System.out.println(strList);
    }
}
