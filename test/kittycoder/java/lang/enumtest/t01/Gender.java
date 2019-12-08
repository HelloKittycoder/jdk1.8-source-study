package kittycoder.java.lang.enumtest.t01;

/**
 * Created by shucheng on 2019-12-8 上午 10:37
 * 这里和普通类的差别：产生Gender对象的方式不同，枚举类的实例只能是枚举值，
 * 而不是随意地通过new来创建枚举类对象
 *
 * 但存在的问题是：name被定义成public，可以被直接操作
 */
public enum Gender {
    MALE,FEMALE;
    // 定义一个public修饰的实例变量
    public String name;
}
