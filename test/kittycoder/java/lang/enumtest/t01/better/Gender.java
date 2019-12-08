package kittycoder.java.lang.enumtest.t01.better;

/**
 * Created by shucheng on 2019-12-8 上午 10:32
 * 这里对之前的Gender做了改进：把name设置成private，从而避免其他程序直接访问该name成员变量，
 * 必须通过setName()方法来修改Gender实例的name变量，而setName()方法就可以保证不会产生混乱。
 *
 * 但存在的问题是：枚举类通常应该设计成不可变类，也就是说，成员变量值不应该允许改变，
 * 这样会更安全，而且代码更加简洁
 */
public enum Gender {
    MALE,FEMALE;
    private String name;
    public void setName(String name) {
        switch (this) {
            case MALE:
                if ("男".equals(name)) {
                    this.name = name;
                } else {
                    System.out.println("参数错误");
                    return;
                }
                break;
            case FEMALE:
                if ("女".equals(name)) {
                    this.name = name;
                } else {
                    System.out.println("参数错误");
                    return;
                }
                break;
        }
    }
    public String getName() {
        return this.name;
    }
}
