package kittycoder.java.lang.enumtest;

/**
 * Created by shucheng on 2019-12-8 上午 11:45
 */
public class SeasonTest {

    public static void print(Season s) {
        System.out.println(s.getName() + "，这真是一个" + s.getDesc() + "的季节");
    }

    public static void main(String[] args) {
        print(Season.FALL);
    }
}
