package kittycoder.java.util;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

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

    // 测试remove(int)和remove(Object)方法
    @Test
    public void testRemove() {
        stringList = generateList();
        String removedStr = stringList.remove(1);
        System.out.println("元素" + removedStr + "被移除，当前list为" + stringList);
        boolean isRemoved = stringList.remove("33");
        System.out.println("元素33" + "移除" + (isRemoved ? "成功" : "失败")
                            + "，当前list为" + stringList);
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

    // 测试addAll(Collection)方法
    @Test
    public void testAddAll() {
        stringList = generateList();
        List<String> strList2 = new ArrayList<>();
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
        List<String> strList2 = new ArrayList<>();
        strList2.add("44");
        strList2.add("55");
        strList2.add("66");
        System.out.println("旧的stringList为" + stringList);
        boolean isAdded = stringList.addAll(1, strList2);
        System.out.println("strList2" + strList2 + "添加" + (isAdded ? "成功" : "失败")
                + "，新的stringList为" + stringList);
    }

    // 测试removeAll方法
    @Test
    public void testRemoveAll() {
        stringList = generateList2();
        List<String> specList = generateList();
        System.out.println("旧的stringList为" + stringList);
        boolean isRemoved = stringList.removeAll(specList);
        System.out.println("specList" + specList + "移除" + (isRemoved ? "成功" : "失败")
                + "，新的stringList为" + stringList);
    }

    // 测试retainAll方法
    @Test
    public void testRetainAll() {
        stringList = generateList2();
        List<String> specList = generateList();
        System.out.println("旧的stringList为" + stringList);
        boolean isRetained = stringList.retainAll(specList);
        System.out.println("specList" + specList + "保留" + (isRetained ? "成功" : "失败")
                + "，新的stringList为" + stringList);
    }

    // 测试removeIf方法
    @Test
    public void testRemoveIf() {
        stringList = generateList2();
        System.out.println("旧的stringList为" + stringList);
        boolean isRemoved = stringList.removeIf(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                if ("11".equals(s)) return true;
                return false;
            }
        });
        // 可以简写为：
        // boolean isRemoved = stringList.removeIf(s->"11".equals(s));
        System.out.println("有元素被移除" + isRemoved + "，新的stringList为" + stringList);
    }

    // 测试replaceAll方法
    @Test
    public void testReplaceAll() {
        stringList = generateList2();
        System.out.println("旧的stringList为" + stringList);
        stringList.replaceAll(new UnaryOperator<String>() {
            @Override
            public String apply(String s) {
                if ("11".equals(s)) return "hehe";
                // 下面这行加上会报ConcurrentModificationException，因为中途修改了size
                // if ("22".equals(s)) stringList.remove("22");
                return s;
            }
        });
        // 可以简写为：
        /*stringList.replaceAll(s -> {
            if ("11".equals(s)) return "hehe";
            return s;
        });*/
        System.out.println("新的stringList为" + stringList);
    }

    // 测试sort方法
    @Test
    public void testSort() {
        stringList = generateList2();
        System.out.println("排序前，stringList为" + stringList);
        stringList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int a = Integer.parseInt(o1);
                int b = Integer.parseInt(o2);
                // 倒序排列
                return Integer.compare(b, a);
            }
        });
        // 可以简写为：
        /*stringList.sort((o1, o2) -> {
            int a = Integer.parseInt(o1);
            int b = Integer.parseInt(o2);
            // 倒序排列
            return Integer.compare(b, a);
        });*/
        System.out.println("排序后，stringList为" + stringList);
    }

    // 测试subList方法
    @Test
    public void testSubList() {
        stringList = generateList2();
        System.out.println("stringList为" + stringList);
        // 截取list中索引范围为[3,5)的元素
        List<String> subList = stringList.subList(3, 5);
        System.out.println("subList为" + subList);
    }

    // 测试增强for循环
    @Test
    public void testEnhancedforloop() {
        // ArrayList的增强for循环：ArrayList实现了Iterable接口，
        // 内部定义了一个Itr用来返回当前list的迭代器
        stringList = new ArrayList<>();
        for (String str: stringList) {
            System.out.println(str);
        }
    }

    // 测试传入Collection的构造器
    @Test
    public void testConstructorWithCollection() {
        List<String> strList = generateList();
        // 通过List构造出一个list
        List<String> cc1 = new ArrayList<>(strList);
        System.out.println(cc1);

        // 通过Set构造出一个list
        Set<String> strSet = new HashSet<>();
        strSet.add("s1");
        strSet.add("s2");
        strSet.add("s3");
        List<String> cc2 = new ArrayList<>(strSet);
        System.out.println(cc2);

        Set<Student> stuSet = new HashSet<>();
        Student s;
        s = new Student(1, "s1");
        stuSet.add(s);
        s = new Student(2, "s2");
        stuSet.add(s);
        s = new Student(3, "s3");
        stuSet.add(s);
        List<Student> ccStu = new ArrayList<>(stuSet);
        System.out.println(ccStu);
    }

    // 测试trimToSize方法
    @Test
    public void testTrimToSize() throws Exception {
        List<String> strList = (ArrayList<String>) generateList();
        Field elementDataField = ArrayList.class.getDeclaredField("elementData");
        elementDataField.setAccessible(true);
        Object[] elementData = (Object[]) elementDataField.get(strList);
        System.out.println("修改前====list的size为" + strList.size() +
                "，elementData的length为" + elementData.length);
        ((ArrayList<String>)strList).trimToSize();
        elementData = (Object[]) elementDataField.get(strList);
        System.out.println("修改后====list的size为" + strList.size() +
                "，elementData的length为" + elementData.length);
    }

    private List<String> generateList() {
        List<String> strList = new ArrayList<>();
        strList.add("11");
        strList.add("22");
        strList.add("33");
        return strList;
    }

    private List<String> generateList2() {
        List<String> strList = new ArrayList<>();
        strList.add("22");
        strList.add("11");
        strList.add("33");
        strList.add("11");
        strList.add("44");
        strList.add("11");
        strList.add("55");
        return strList;
    }

    private static class Student {
        private Integer id;
        private String name;

        public Student() {
        }

        public Student(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}