package kittycoder.java.lang;

/**
 * Created by shucheng on 2019-11-6 上午 9:14
 * 使用join的时候
 * main线程会等待threadA，main线程会等待threadA执行完之后结束掉
 *
 * threadA暂停时，会影响main线程printNum方法的执行
 */
public class TestJoin2 {

    public static void printNum() {
        for (int i = 0; i < 20; i++) {
            System.out.println(Thread.currentThread().getName() + "====" + i);
        }
    }

    public static void main(String[] args) {
        System.out.println("MainThread run start.");

        Thread threadA = new Thread(new Runnable() {
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
        threadA.start();

        System.out.println("MainThread join before");
        try {
            threadA.join();
            /*
            //上面的join和下面的代码大致相同，为什么说大致呢：
            //（1）执行后的效果是一样的
            //（2）但是锁的对象不同，上面锁的是join方法，下面锁的是threadA整个对象
            synchronized (threadA) {
                // 另外再注意一点，不能直接去执行wait方法
                // 执行wait方法的前提是当前执行线程获得了锁，比如这里就是获得了threadA对象的锁
                while (threadA.isAlive()) { // 这里是判断threadA已经调用start方法，并且threadA不是处在死亡状态
                    // 这里调用wait方法，虽然是用threadA来调用的，但是不要认为执行线程是threadA，
                    // 这里的执行线程依然是main线程，所以这里暂停的是main线程
                    threadA.wait(0);
                }
            }*/
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printNum();
        System.out.println("MainThread finished");
    }
}
