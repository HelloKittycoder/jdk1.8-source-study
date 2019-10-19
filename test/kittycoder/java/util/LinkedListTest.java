package kittycoder.java.util;

import org.junit.Test;

import java.util.Deque;
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

    // 测试addFirst方法
    @Test
    public void testAddFirst() {
        List<String> stringList = generateList();
        System.out.println("旧的stringList为" + stringList);
        ((Deque<String>)stringList).addFirst("hehe");
        System.out.println("新的stringList为" + stringList);
    }

    // 测试addLast方法
    @Test
    public void testAddLast() {
        List<String> stringList = generateList();
        System.out.println("旧的stringList为" + stringList);
        ((Deque<String>)stringList).addLast("hehe");
        System.out.println("新的stringList为" + stringList);
    }

    // 获取头节点（index=0）数据方法：
    // getFirst，element，peek，peekFirst
    // 说明：getFirst和element一样，peek和peekFirst一样；
    // 这四个获取头节点方法的区别在于对链表为空时的处理，是抛出异常还是返回null，其中getFirst()和element()方法会在链表为空时，抛出异常

    private List<String> generateList() {
        List<String> strList = new LinkedList<>();
        strList.add("11");
        strList.add("22");
        strList.add("33");
        return strList;
    }
}