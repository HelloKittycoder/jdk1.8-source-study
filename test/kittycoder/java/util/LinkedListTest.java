package kittycoder.java.util;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by shucheng on 2019-10-18 上午 8:36
 */
public class LinkedListTest {

    private List<String> stringList;

    // 将指定元素添加到链表末尾
    @Test
    public void testAdd() {
        List<String> stringList = generateList();
        System.out.println(stringList);
    }

    // 给指定位置添加元素
    @Test
    public void testAddIndex() {
        List<String> stringList = generateList();
        stringList.add(1, "hehe");
        System.out.println(stringList);
    }

    // 测试addAll(Collection)方法
    @Test
    public void testAddAll() {
        List<String> stringList = generateList();
        List<String> strList2 = new LinkedList<>();
        strList2.add("44");
        strList2.add("55");
        strList2.add("66");
        System.out.println("旧的stringList为" + stringList);
        boolean isAdded = stringList.addAll(strList2);
        System.out.println("strList2" + strList2 + "添加" + (isAdded ? "成功" : "失败")
                + "，新的stringList为" + stringList);
    }

    // 测试addAll(int, Collection)方法
    @Test
    public void testIndexAddAll() {
        stringList = generateList();
        List<String> strList2 = new LinkedList<>();
        strList2.add("44");
        strList2.add("55");
        strList2.add("66");
        System.out.println("旧的stringList为" + stringList);
        boolean isAdded = stringList.addAll(1, strList2);
        System.out.println("strList2" + strList2 + "添加" + (isAdded ? "成功" : "失败")
                + "，新的stringList为" + stringList);
    }

    private List<String> generateList() {
        List<String> strList = new LinkedList<>();
        strList.add("11");
        strList.add("22");
        strList.add("33");
        return strList;
    }
}
