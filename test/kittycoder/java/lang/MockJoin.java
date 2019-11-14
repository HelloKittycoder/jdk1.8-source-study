package kittycoder.java.lang;

/**
 * Created by shucheng on 2019-11-14 下午 20:58
 * 简单模拟下join，可以结合TestJoin2来看
 *
 * 参考链接：
 * 1. join是如何执行的
 * https://stackoverflow.com/questions/23422970/how-does-join-work-in-java-does-it-guarantee-the-execution-before-main/23422990
 * 2. Thread.join里wait()为什么要循环调用
 * https://segmentfault.com/q/1010000016891717
 * 3. https://www.cnblogs.com/nulisaonian/p/6076674.html
 */
public class MockJoin {

    public static void main(String[] args) {

        System.out.println("MainThread started：" +Thread.currentThread().getName());

        TestThread testThread = new TestThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("ThreadA run start.");
                try {
                    System.out.println("threadA暂停1秒 start...");
                    Thread.sleep(1000);
                    System.out.println("threadA暂停1秒 end...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ThreadA run finished.");
            }
        });
        testThread.start();
        try {
            testThread.myjoin();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printNum();

        System.out.println("MainThread finished：" +Thread.currentThread().getName());
    }

    public static void printNum() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "====" + i);
        }
    }

    static class TestThread extends Thread {

        public TestThread() {
            super();
        }

        public TestThread(Runnable target) {
            super(target);
        }

        public synchronized void myjoin() throws InterruptedException {
            // 可以看出wait方法使main线程暂停了
            System.out.println("当前执行的线程为：" + Thread.currentThread().getName());
            System.out.println("被调用的对象为：" + getName());
            while (isAlive()) {
                wait(0);
            }
        }
    }
}
