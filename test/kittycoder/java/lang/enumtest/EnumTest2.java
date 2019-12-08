package kittycoder.java.lang.enumtest;

import org.junit.Test;

/**
 * Created by shucheng on 2019-12-8 下午 12:02
 */
public class EnumTest2 {

    @Test
    public void testName() {
        System.out.println(SeasonEnum.SPRING.name()); // SPRING
        System.out.println(SeasonEnum.SUMMBER.name()); // SUMMBER
    }

    @Test
    public void testOrdinal() {
        System.out.println(SeasonEnum.SPRING.ordinal()); // 0
        System.out.println(SeasonEnum.SUMMBER.ordinal()); // 1
    }

    @Test
    public void testCompareTo() {
        System.out.println(SeasonEnum.SPRING.compareTo(SeasonEnum.SUMMBER)); // 负数
        System.out.println(SeasonEnum.SUMMBER.compareTo(SeasonEnum.SPRING)); // 正数
        System.out.println(SeasonEnum.SPRING.compareTo(SeasonEnum.SPRING)); // 0
    }

    @Test
    public void testToString() {
        System.out.println(SeasonEnum.SPRING);
        System.out.println(SeasonEnum.SUMMBER);
    }

    @Test
    public void testGetDeclaringClass() {
        Class c1 = SeasonEnum.SPRING.getDeclaringClass();
        Class c2 = SeasonEnum.SUMMBER.getDeclaringClass();
        System.out.println(c1);
        System.out.println(c2);
    }

    @Test
    public void testValueOf() {
        SeasonEnum spring1 = Enum.valueOf(SeasonEnum.class, "SPRING");
        System.out.println(spring1);
        // SeasonEnum.valueOf是jdk编译枚举类时自动加的一个静态方法，
        // 实际调用的还是Enum.valueOf方法，只不过方法里帮我们把SeasonEnum.class传过去了
        SeasonEnum spring2 = SeasonEnum.valueOf("SPRING");
        System.out.println(spring2);
        System.out.println(spring1 == spring2);
    }
}
