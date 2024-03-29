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
        stringList = generateList();
        System.out.println("旧的stringList为" + stringList);
        ((Deque<String>)stringList).addFirst("hehe");
        System.out.println("新的stringList为" + stringList);
    }

    // 测试addLast方法
    @Test
    public void testAddLast() {
        stringList = generateList();
        System.out.println("旧的stringList为" + stringList);
        ((Deque<String>)stringList).addLast("hehe");
        System.out.println("新的stringList为" + stringList);
    }

    // 测试indexOf和lastIndexOf方法
    @Test
    public void testIndex() {
        stringList = generateList2();
        int index;
        index = stringList.indexOf("11");
        System.out.println("元素11首次出现的索引为：" + index); // 1
        index = stringList.lastIndexOf("11");
        System.out.println("元素11最后一次出现的索引为：" + index); // 5
    }

    // 测试remove(int)和remove(Object)方法
    @Test
    public void testRemove() {
        stringList = generateList2();
        System.out.println("stringList为" + stringList);
        boolean isRemoved = stringList.remove("11");
        System.out.println("元素11移除" + (isRemoved ? "成功" : "失败")
                + "，当前list为" + stringList);
        String removedStr = stringList.remove(1);
        System.out.println("索引为1的元素" + removedStr + "被移除，当前list为" + stringList);
    }

    // 测试removeFirst方法
    @Test
    public void testRemoveFirst() {
        stringList = generateList();
        System.out.println("旧的stringList为" + stringList);
        String removedStr = ((Deque<String>)stringList).removeFirst();
        System.out.println("元素" + removedStr + "被移除");
        System.out.println("新的stringList为" + stringList);
    }

    // 测试removeLast方法
    @Test
    public void testRemoveLast() {
        stringList = generateList();
        System.out.println("旧的stringList为" + stringList);
        String removedStr = ((Deque<String>)stringList).removeLast();
        System.out.println("元素" + removedStr + "被移除");
        System.out.println("新的stringList为" + stringList);
    }

    // 获取头节点（index=0）数据方法：
    // getFirst，element，peek，peekFirst
    // 说明：getFirst和element一样，peek和peekFirst一样；
    // 这四个获取头节点方法的区别在于对链表为空时的处理，是抛出异常还是返回null，
    // 其中getFirst()和element()方法会在链表为空时，抛出NoSuchElementException，
    // peek()和peekFirst()只是会返回null

    // 获取尾节点（index=-1）数据方法：
    // getLast，peekLast
    // 说明：getLast()方法在链表为空时，会抛出NoSuchElementException，而peekLast()则不会，只是会返回null

    // 删除头节点：
    // remove，pop，removeFirst，poll，pollFirst
    // 说明：remove，pop，removeFirst都是一样的
    // 这五个删除头节点方法的区别在于对链表头节点为空时的处理，是抛出异常还是返回null，
    // 其中remove()，pop()，removeFirst()在链表为空时，会抛出NoSuchElementException，
    // 而poll()和pollFirst()则不会，只是会返回null

    // 删除尾节点：
    // removeLast，pollLast
    // 说明：removeLast()方法在链表为空时，会抛出NoSuchElementException，而pollLast()则不会，只是会返回null

    private List<String> generateList() {
        List<String> strList = new LinkedList<>();
        strList.add("11");
        strList.add("22");
        strList.add("33");
        return strList;
    }

    private List<String> generateList2() {
        List<String> strList = new LinkedList<>();
        strList.add("22");
        strList.add("11");
        strList.add("33");
        strList.add("11");
        strList.add("44");
        strList.add("11");
        strList.add("55");
        return strList;
    }
}
