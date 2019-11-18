package kittycoder.java.lang;

import java.lang.reflect.Field;

/**
 * Created by shucheng on 2019-11-15 下午 22:36
 * 简单模拟下ThreadLocal中的类的关系
 * MyClass相当于Thread类，OuterClass相当于ThreadLocal类，InnerClass相当于ThreadLocalMap类
 *
 * 模拟这个类的关系的原因：
 * 因为一开始在看Thread类里的threadLocals属性（是ThreadLocal的内部类ThreadLocalMap的实例）时，
 * 发现竟然无法获取到threadLocals属于哪个ThreadLocal，后面把关系捋了一遍，顺便复习了下内部类。
 * 之所以会出现无法获取到threadLocals属于哪个ThreadLocal，是因为ThreadLocalMap是一个静态内部类，它不需要
 * 依附于某个已经创建好的外部类对象
 *
 * ThreadLocal类的关系图可以参考：
 * https://blog.csdn.net/bntX2jSQfEHy7/article/details/78301098
 */
public class ThreadLocalClassRelationTest {

    private static OuterClass outerClass = new OuterClass();

    public static void main(String[] args) {
        /* 把InnerClass前面的static去掉，这两段注释掉的代码就能用了
        OuterClass o = new OuterClass();
        OuterClass.InnerClass innerClass1 = o.new InnerClass();
        OuterClass.InnerClass innerClass2 = o.new InnerClass();
        System.out.println(innerClass1);
        System.out.println(innerClass2);

        MyClass m = new MyClass();
        OuterClass o = new OuterClass();
        OuterClass.InnerClass innerClass1 = o.new InnerClass();
        m.setInnerClass(innerClass1);
        System.out.println(m);*/

        MyClass m = new MyClass();
        outerClass.set(11, m);
        System.out.println(m);

    }

    public static Object getOuterObject(Object innerObject) {
        Field outerField;
        try {
            outerField = innerObject.getClass().getDeclaredField("this$0");
            outerField.setAccessible(true);
            return outerField.get(innerObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

class MyClass {
    OuterClass.InnerClass innerClass;

    public OuterClass.InnerClass getInnerClass() {
        return innerClass;
    }

    public void setInnerClass(OuterClass.InnerClass innerClass) {
        this.innerClass = innerClass;
    }
}

class OuterClass {

    private int outerId;

    public int getOuterId() {
        return outerId;
    }

    public void setOuterId(int outerId) {
        this.outerId = outerId;
    }

    void set(Object value, MyClass mc) {
        createMyMap(mc);
    }

    void createMyMap(MyClass mc) {
        mc.innerClass = new InnerClass();
    }

    static class InnerClass {
        private int innerId;

        public int getInnerId() {
            return innerId;
        }

        public void setInnerId(int innerId) {
            this.innerId = innerId;
        }
    }
}
