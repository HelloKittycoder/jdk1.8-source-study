package kittycoder.java.lang;

/**
 * Created by shucheng on 2019-11-15 上午 11:24
 * 测试yield方法
 *
 * 参考链接：
 * https://blog.csdn.net/xzp_12345/article/details/81129735
 * https://blog.csdn.net/youanyyou/article/details/84282668
 * https://blog.csdn.net/river66/article/details/91492758
 */
public class ThreadTest3 {

    /**
     * 测试两个相同优先级的线程yield后的情况（没啥规律，这个看系统分配）
     */
    /*public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 20; i++) {
                    System.out.println(Thread.currentThread().getName() + "-----" + i);
                    // 每执行完5个之后就让出CPU
                    if (i % 5 == 0) {
                        Thread.yield();
                    }
                }
            }
        };
        new Thread(runnable, "t1").start();
        new Thread(runnable, "t2").start();
    }*/

    // 测试在yield后，高、低优先级获得的执行机会（从执行结果来看，yield后的执行情况没啥规律，
    // 并不一定是高优先级的就会获得更多执行机会）
    /*public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 20; i++) {
                    System.out.println(Thread.currentThread().getName() + "-----" + i);
                    // 每执行完5个之后就让出CPU
                    if (i % 5 == 0) {
                        Thread.yield();
                    }
                }
            }
        };
        Thread t1 = new Thread(runnable, "t1");
        Thread t2 = new Thread(runnable, "t2");

        // 将t1设置为最低优先级
        t1.setPriority(Thread.MIN_PRIORITY);
        // 将t2设置为最高优先级
        t2.setPriority(Thread.MAX_PRIORITY);

        // 执行过程中，如果某个线程发生yield，t2将有可能获得更多的执行机会
        t1.start();
        t2.start();
    }*/

    // 测试yield是否会释放锁
    public static void main(String[] args) {
        ThreadTest3 tt = new ThreadTest3();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                tt.a1();
            }
        };

        Thread t1 = new Thread(runnable, "t1");
        Thread t2 = new Thread(runnable, "t2");
        t1.start();
        t2.start();
    }

    public synchronized void a1() {
        for (int i = 0; i <= 100; i++) {
            System.out.println(Thread.currentThread().getName() + "-----" + i);
            // 每执行完5个之后就让出CPU，但是不会释放锁
            if (i % 5 == 0) {
                Thread.yield();
            }
        }
    }
}
