package kittycoder.java.lang.synchronizedtest;

/**
 * Created by shucheng on 2019-11-18 下午 20:33
 *
 * 简单探讨下在给set方法加synchronized时，同时也需要给get方法加synchronized的必要性：
 * 假设有两个线程t1、t2，t1先去访问set方法（花的时间比较长），t2后访问get方法（但时间不长），
 * 正常情况下，应该是t2访问的结果为t1修改完毕后的值
 * 换句话说，就是后进来的读线程应该读到的是先进去的写线程更新后的值
 *
 * 基于上面这个说法，我认为才存在同时也给get方法加synchronized的必要性
 *
 * 如果get方法不加synchronized，将会打印“t2==aa的值为1”（不符合预期），此时t2不会等t1执行完再执行
 * 如果get方法加了synchronized，将会打印“t2==aa的值为11”（符合预期），此时t2会等t1执行完再执行（因为t1拿到了this的锁，而t2锁的也是this）
 *
 * 参考链接：https://stackoverflow.com/questions/22841368/are-java-getters-thread-safe
 * https://javarevisited.blogspot.com/2011/04/synchronization-in-java-synchronized.html
 */
public class SynchronizedTest1 {

    private int aa = 1;

    public synchronized int getAa() {
        return aa;
    }

    public synchronized void setAa(int aa) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.aa = aa;
    }

    public static void main(String[] args) {
        SynchronizedTest1 st = new SynchronizedTest1();
        System.out.println("aa的初始值：" + st.getAa());

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "start");
                st.setAa(11);
                System.out.println(Thread.currentThread().getName() + "end");
            }
        }, "t1");
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "==aa的值为" + st.getAa());
                st.setAa(22);
            }
        }, "t2");

        t1.start();
        // 这里为了确保t2在t1之后运行，让main线程暂停了100毫秒
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}