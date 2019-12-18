package kittycoder.java.lang.exceptionhandle;

import org.junit.Test;

/**
 * Created by shucheng on 2019/12/18 21:26
 */
public class ExceptionTest1 {

    // Throwable类的常用方法
    @Test
    public void test1() {
        try {
            System.out.println(1/0);
        } catch (Exception e) {
            // 返回异常发生时的简要描述
            System.out.println("简要描述：" + e.getMessage());
            // 返回异常发生时的详细信息
            System.out.println("详细信息：" + e.toString());
            // 在控制台上打印Throwable对象封装的异常信息
            e.printStackTrace();
        }
    }
}
