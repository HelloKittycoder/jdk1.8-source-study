package kittycoder.java.lang.enumtest.t01.best;

/**
 * Created by shucheng on 2019-12-8 上午 11:09
 * better里提出：成员变量值不应该允许改变，也就是说枚举类的成员变量要使用private final修饰
 * 如果将所有的成员变量都使用了final修饰符来修饰，那就需要在构造器里为这些成员变量指定初始值
 * （或者在定义成员成员变量时指定默认值，或者在初始化块中指定初始值）。但后两种情况并不常见，因此应该为
 * 枚举类显式定义带参数的构造器
 */
public enum  Gender {
    // 此处的枚举值必须调用对应的构造器来创建
    MALE("男"),FEMALE("女");
    private final String name;
    // 枚举类的构造器只能使用private修饰（默认就是private，这里可以不加private）
    private Gender(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
}
