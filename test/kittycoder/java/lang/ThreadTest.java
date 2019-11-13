package kittycoder.java.lang;

/**
 * Created by shucheng on 2019-11-12 下午 22:53
 * 参考链接：
 * 构造方法：https://blog.csdn.net/u014730165/article/details/81980870
 * https://blog.csdn.net/qz_zhiren/article/details/88367994
 *
 * start：https://blog.csdn.net/weixin_43490440/article/details/101519957
 */
public class ThreadTest {

    // 测试构造方法
    public static void main(String[] args) {
        // 采用继承Thread的方法来创建线程
        Thread t1 = new MyThread();
        t1.start();
        Thread t2 = new MyThread("t2");
        t2.start();

        // 传入Runnable接口实现
        Thread m1 = new Thread(new MyRun());
        m1.start();
        Thread m2 = new Thread(new MyRun(), "m2");
        m2.start();

        // 传入线程组，Thread.currentThread().getName()
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        Thread g1 = new Thread(threadGroup, new MyRun());
        g1.start();
        ThreadGroup tg2 = new ThreadGroup("tg2");
        Thread g2 = new Thread(tg2, new MyRun(), "g2");
        g2.start();
    }

    static class MyThread extends Thread {
        public MyThread() {
            super();
        }

        public MyThread(String name) {
            super(name);
        }

        // 因为MyThread继承了Thread，
        // 所以可以不用写Thread.currentThread().getName()，直接调getName就行了
        @Override
        public void run() {
            System.out.println(getThreadGroup() + "=="
                    + getName() + "==MyThread==" + getState());
        }
    }

    static class MyRun implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getThreadGroup() + "=="
                    + Thread.currentThread().getName() + "==MyRun=="
                    + Thread.currentThread().getState());
        }
    }
}