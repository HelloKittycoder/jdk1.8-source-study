package kittycoder.java.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shucheng on 2019-10-8 下午 23:34
 */
public class ArrayListTest {

    private List<String> stringList;

    // 将指定元素添加到list末尾
    @Test
    public void add() {
        stringList = generateList();
        System.out.println(stringList);
    }

    // 给指定位置添加index
    @Test
    public void addIndex() {
        stringList = generateList();
        stringList.add(1, "444");
        System.out.println(stringList);
    }

    // 测试set和get方法
    @Test
    public void testSetGet() {
        stringList = generateList();
        String oldValue = stringList.set(1, "set后的值");
        System.out.println("index为1的旧元素为：" + oldValue);
        System.out.println("index为1的新元素为：" + stringList.get(1));
        // System.out.println(stringList.get(-1));
    }

    @Test
    public void testRemove() {
        stringList = generateList();
        String removedStr = stringList.remove(1);
        System.out.println("元素" + removedStr + "被移除，当前list为" + stringList);
        boolean isRemoved = stringList.remove("33");
        System.out.println("元素33" + "移除" + (isRemoved ? "成功" : "失败")
                            + "，当前list为" + stringList);
    }

    private List<String> generateList() {
        List<String> strList = new ArrayList<>();
        strList.add("11");
        strList.add("22");
        strList.add("33");
        return strList;
    }
}