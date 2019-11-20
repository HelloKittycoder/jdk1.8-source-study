package kittycoder.java.lang;

/**
 * Created by shucheng on 2019-11-15 下午 16:51
 * ThreadLocal的作用：
 * 以空间（每个线程都维护一个ThreadLocalMap来存放变量值，key是ThreadLocal，value是变量值）来换时间（执行效率）
 *
 * 以ThreadLocalDemo为例，其实要实现的需求是不同线程访问属性ti，都能正确返回设置过的整数
 * 如果ti只是定义成 public Integer ti = new Integer(1); 显然是不行的，
 * 多线程下要保证设置以后还能正确返回，就需要再添加（下面get方法没有加synchronized，这个地方网上都说要加，
 * 我目前还没明白加这个到底有啥意义，这个暂时先不管）：
 * public Integer getTi() {
 *     return ti;
 * }
 *
 * public synchronized void setTi(Integer ti) {
 *     this.ti = ti;
 * }
 *
 * 这里我们会发现，在调用set方法时，多个线程需要排队等待，影响执行的效率
 *
 * 那么现在使用ThreadLocal，问题就简单了，对于不同的线程来设置ti的值，不需要加锁，因为针对每个Thread，都会维护一个
 * ThreadLocalMap，操作的是各自线程的ThreadLocalMap，不存在资源竞争问题
 *
 * 有关ThreadLocalMap的理解参考 https://www.cnblogs.com/micrari/p/6790229.html
 */
public class ThreadLocalDemo {

    // 定义一个全局变量，来存放线程需要的变量
    public static ThreadLocal<Integer> ti = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 222;
        }
    };

    public static ThreadLocal<Integer> ti2 = new ThreadLocal<>();

    public static void main(String[] args) {
        ti.set(11);
        // System.out.println(ti.get());
        ti.get();

        // 创建两个线程
        /*for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Double d = Math.random()*10;
                    // 存入当前线程独有的值
                    System.out.println(Thread.currentThread().getName());
                    ti.set(d.intValue());
                    new A().get();
                    new B().get();
                }
            }).start();
        }*/

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Double d = Math.random()*10;
                // 存入当前线程独有的值
                System.out.println(Thread.currentThread().getName());
                ti.set(d.intValue());
                ti.set(d.intValue());
                ti2.set(5);
                new A().get();
                new B().get();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Double d = Math.random()*10;
                // 存入当前线程独有的值
                System.out.println(Thread.currentThread().getName());
                ti.set(d.intValue());
                new A().get();
                new B().get();
            }
        });

        t1.start();
        t2.start();
        System.out.println("main线程结束");
        System.out.println("aaa===" + (2 & 31));
    }

    static class A {
        public void get() {
            // 取得当前线程所需要的值
            // System.out.println(Thread.currentThread().getName() + "==A==" + ti.get());
            ti.get();
        }
    }

    static class B {
        public void get() {
            // 取得当前线程所需要的值
            // System.out.println(Thread.currentThread().getName() + "==B==" + ti.get());
            ti.get();
        }
    }
}
