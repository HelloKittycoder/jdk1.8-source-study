package kittycoder.java.lang;

import org.junit.Test;

/**
 * Created by shucheng on 2019-10-27 下午 16:43
 */
public class StringBuilderTest {

    /** =========================构造器=========================== **/
    @Test
    public void testConstructor1() {
        StringBuilder sbuilder = new StringBuilder();
        System.out.println("容量为：" + sbuilder.capacity());

        StringBuilder sbuilder2 = new StringBuilder(5);
        System.out.println("容量为：" + sbuilder2.capacity());

        StringBuilder sbuilder3 = new StringBuilder("张三");
        System.out.println("容量为：" + sbuilder3.capacity());
    }

    @Test
    public void testConstructor2() {
        StringBuilder sbuilder = new StringBuilder("aaa");
        StringBuilder sbuilder2 = new StringBuilder(sbuilder);
        System.out.println(sbuilder2);
    }
    /** =========================构造器=========================== **/

    /** =========================append方法start=========================== **/
    @Test
    public void testAppend1() {
        // append(Object)
        StringBuilder sbuilder = new StringBuilder();
        sbuilder.append((Object) null);
        System.out.println(sbuilder); // null

        sbuilder.append(new Student(1, "张三"));
        System.out.println(sbuilder); // nullStudent{id=1, name='张三'}
    }

    @Test
    public void testAppend2() {
        // append(String)
        StringBuilder sbuilder = new StringBuilder();
        sbuilder.append((String) null);
        System.out.println(sbuilder); // null

        sbuilder.append("AA");
        System.out.println(sbuilder); // nullAA

        sbuilder.append("BB");
        System.out.println(sbuilder); // nullAABB

        sbuilder.append(new Student(1, "张三").toString());
        System.out.println(sbuilder); // nullAABBStudent{id=1, name='张三'}
    }
    /** =========================append方法end=========================== **/

    /** =========================delete方法start=========================== **/
    @Test
    public void testDelete() {
        StringBuilder sbuilder = new StringBuilder("ABC123");
        sbuilder.delete(0, 3);
        System.out.println(sbuilder); // 123
    }

    @Test
    public void testDeleteCharAt() {
        StringBuilder sbuilder = new StringBuilder("ABC123");
        sbuilder.deleteCharAt(1);
        System.out.println(sbuilder); // AC123
    }
    /** =========================delete方法end=========================== **/

    static class Student {
        private int id;
        private String name;

        public Student() {
        }

        public Student(int id, String name) {
            this.id = id;
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
