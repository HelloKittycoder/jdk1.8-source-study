package kittycoder.java.lang.enumtest.t01.better;

/**
 * Created by shucheng on 2019-12-8 上午 10:35
 */
public class GenderTest {
    public static void main(String[] args) {
        // 下面这句等效于：Gender g = Enum.valueOf(Gender.class, "FEMALE");
        Gender g = Gender.valueOf("FEMALE");
        g.setName("女");
        System.out.println(g + "代表：" + g.getName());
        // 此时设置name值时将会提示参数错误
        g.setName("男");
        System.out.println(g + "代表：" + g.getName());
    }
}
