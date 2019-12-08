package kittycoder.java.lang.enumtest.t02;

/**
 * Created by shucheng on 2019-12-8 上午 11:25
 */
public class GenderTest {
    public static void main(String[] args) {
        Gender g = Gender.valueOf("MALE");
        System.out.println(g.getName());
        g.info();
    }
}
