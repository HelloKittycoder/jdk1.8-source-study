package kittycoder.java.lang;


/**
 * Created by shucheng on 2019-11-15 上午 9:07
 * 测试sleep方法
 */
public class ThreadTest2 {

    public static void main(String[] args) {
        System.out.println("MainThread started");

        System.out.println("MainThread暂停1秒 start...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MainThread暂停1秒 end...");

        ThreadTest2 tt = new ThreadTest2();
        // 由于t1和t2同时调用tt对象的a1方法（同步），
        // （1）t1执行a1方法时，已经获得了tt对象的锁，进行sleep时，不会释放锁，
        // 所以t2无法执行a1方法
        // （2）当t1方法执行完毕后，t2获得执行机会，执行a1方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    tt.a1();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "t1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    tt.a1();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();

        System.out.println("MainThread finished");
    }

    public synchronized void a1() throws Exception {
        System.out.println("executing a1 method start..." + Thread.currentThread().getName());
        Thread.sleep(3000);
        System.out.println("executing a1 method end..." + Thread.currentThread().getName());
    }
}
