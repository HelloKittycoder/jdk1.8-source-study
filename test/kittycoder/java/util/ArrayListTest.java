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
}