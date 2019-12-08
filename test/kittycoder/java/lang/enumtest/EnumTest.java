package kittycoder.java.lang.enumtest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by shucheng on 2019-12-7 下午 23:56
 * 简单测试下枚举类
 */
public class EnumTest {

    public static void main(String[] args) {
        forEnum();
    }

    public static void forEnum() {
        // 打印SeasonEnum继承的父类
        // enum默认继承了java.lang.Enum类，不能再继承别的类了，但是可以实现一个或多个接口
        Class superclass = SeasonEnum.class.getSuperclass(); // java.lang.Enum
        System.out.println(superclass);
        // 打印SeasonEnum中声明的字段
        Field[] fields = SeasonEnum.class.getDeclaredFields();
        for (Field f : fields) {
            System.out.println(f);
        }
        // 打印SeasonEnum中声明的方法
        Method[] methods = SeasonEnum.class.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(m);
        }
        // 枚举类默认有一个values()方法，返回该枚举类的所有实例
        for (SeasonEnum SeasonEnum : SeasonEnum.values()) {
            System.out.println(SeasonEnum + " ordinal " + SeasonEnum.ordinal());
        }
        // 使用枚举实例时，可通过EnumClass.variable形式来访问
        judge(SeasonEnum.SPRING);
    }

    public static void judge(SeasonEnum s) {
        // switch语句里的表达式可以是枚举值
        switch (s) {
            case SPRING:
                System.out.println("春暖花开，正好踏青");
                break;
            case SUMMBER:
                System.out.println("夏日炎炎，适合游泳");
                break;
            case FALL:
                System.out.println("秋高气爽，进补及时");
                break;
            case WINTER:
                System.out.println("冬日雪飘，围炉赏雪");
                break;
        }
    }
}
