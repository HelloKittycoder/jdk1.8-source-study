package kittycoder.java.lang.enumtest.t02;

/**
 * Created by shucheng on 2019-12-8 上午 11:22
 */
public enum Gender implements GenderDesc {
    MALE("男") {
        @Override
        public void info() {
            System.out.println("这个枚举值代表男性");
        }
    },
    FEMALE("女") {
        @Override
        public void info() {
            System.out.println("这个枚举值代表女性");
        }
    };
    private final String name;

    public String getName() {
        return name;
    }

    Gender(String name) {
        this.name = name;
    }

    // Gender枚举类如果实现了接口，Gender枚举类和枚举值其中必须有一个要实现接口方法（当然两个都实现更没问题）
    @Override
    public void info() {
    }
}
