package kittycoder.java.util;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by shucheng on 2019-10-18 上午 8:36
 */
public class LinkedListTest {

    @Test
    public void testAdd() {
        List<String> strList = new LinkedList<>();
        strList.add("11");
        strList.add("22");
        strList.add("33");
        System.out.println(strList);
    }
}
