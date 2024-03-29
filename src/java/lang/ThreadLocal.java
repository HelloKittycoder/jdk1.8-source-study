/*
 * Copyright (c) 1997, 2013, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package java.lang;
import java.lang.ref.*;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * This class provides thread-local variables.  These variables differ from
 * their normal counterparts in that each thread that accesses one (via its
 * {@code get} or {@code set} method) has its own, independently initialized
 * copy of the variable.  {@code ThreadLocal} instances are typically private
 * static fields in classes that wish to associate state with a thread (e.g.,
 * a user ID or Transaction ID).
 *
 * <p>For example, the class below generates unique identifiers local to each
 * thread.
 * A thread's id is assigned the first time it invokes {@code ThreadId.get()}
 * and remains unchanged on subsequent calls.
 * <pre>
 * import java.util.concurrent.atomic.AtomicInteger;
 *
 * public class ThreadId {
 *     // Atomic integer containing the next thread ID to be assigned
 *     private static final AtomicInteger nextId = new AtomicInteger(0);
 *
 *     // Thread local variable containing each thread's ID
 *     private static final ThreadLocal&lt;Integer&gt; threadId =
 *         new ThreadLocal&lt;Integer&gt;() {
 *             &#64;Override protected Integer initialValue() {
 *                 return nextId.getAndIncrement();
 *         }
 *     };
 *
 *     // Returns the current thread's unique ID, assigning it if necessary
 *     public static int get() {
 *         return threadId.get();
 *     }
 * }
 * </pre>
 * <p>Each thread holds an implicit reference to its copy of a thread-local
 * variable as long as the thread is alive and the {@code ThreadLocal}
 * instance is accessible; after a thread goes away, all of its copies of
 * thread-local instances are subject to garbage collection (unless other
 * references to these copies exist).
 *
 * @author  Josh Bloch and Doug Lea
 * @since   1.2
 */
public class ThreadLocal<T> {
    /**
     * ThreadLocals rely on per-thread linear-probe hash maps attached
     * to each thread (Thread.threadLocals and
     * inheritableThreadLocals).  The ThreadLocal objects act as keys,
     * searched via threadLocalHashCode.  This is a custom hash code
     * (useful only within ThreadLocalMaps) that eliminates collisions
     * in the common case where consecutively constructed ThreadLocals
     * are used by the same threads, while remaining well-behaved in
     * less common cases.
     *
     * Thread内部维护了一个ThreadLocalMap（key是ThreadLocal，value是设置的值）
     * threadLocalHashCode是用来解决Thread里ThreadLocal的哈希冲突（通过threadLocalHashCode得到哈希值）
     *
     * threadLocalHashCode是在创建一个ThreadLocal对象时，相应就会生成的（调用nextHashCode()方法），将HASH_INCREMENT记为hi，
     * threadLocalHashCode就是依次从序列hi,2*hi,3*hi,4*hi,...中取值的
     *
     * 顺便来解释下ThreadLocal的结构：
     * 以ThreadLocalDemo为例来说明：ti的hashcode为hi,ti2的hashcode为2*hi
     * 1.main线程调用了ti.set方法，main线程内部维护的ThreadLocalMap存了Entry(ti,11)
     * 2.接下来线程t1、t2启动了（调用不分先后），这里就按先t1后t2的方式来说，
     * t1调用了ti.set（假设值为111）和ti2.set方法，此时t1线程内部维护的ThreadLocalMap存了Entry(ti,111)，Entry(ti2,5)
     * 3.t2调用了ti.set(假设值为222)方法，此时t2线程内部维护的ThreadLocalMap存了Entry(ti,222)
     *
     */
    private final int threadLocalHashCode = nextHashCode();

    /**
     * The next hash code to be given out. Updated atomically. Starts at
     * zero.
     */
    private static AtomicInteger nextHashCode =
        new AtomicInteger();

    /**
     * The difference between successively generated hash codes - turns
     * implicit sequential thread-local IDs into near-optimally spread
     * multiplicative hash values for power-of-two-sized tables.
     */
    private static final int HASH_INCREMENT = 0x61c88647;

    /**
     * Returns the next hash code.
     * 返回ThreadLocal的下一个hashcode
     */
    private static int nextHashCode() {
        return nextHashCode.getAndAdd(HASH_INCREMENT);
    }

    /**
     * Returns the current thread's "initial value" for this
     * thread-local variable.  This method will be invoked the first
     * time a thread accesses the variable with the {@link #get}
     * method, unless the thread previously invoked the {@link #set}
     * method, in which case the {@code initialValue} method will not
     * be invoked for the thread.  Normally, this method is invoked at
     * most once per thread, but it may be invoked again in case of
     * subsequent invocations of {@link #remove} followed by {@link #get}.
     *
     * <p>This implementation simply returns {@code null}; if the
     * programmer desires thread-local variables to have an initial
     * value other than {@code null}, {@code ThreadLocal} must be
     * subclassed, and this method overridden.  Typically, an
     * anonymous inner class will be used.
     *
     * 返回当前线程ThreadLocal变量的初始值
     * 被调用的时机：ThreadLocal没有被当前线程赋值时或当前线程刚调用remove方法后调用get方法
     * 使用方式：写一个ThreadLocal的匿名内部类，然后重写initialValue，用于返回初始值
     *
     * @return the initial value for this thread-local
     */
    protected T initialValue() {
        return null;
    }

    /**
     * Creates a thread local variable. The initial value of the variable is
     * determined by invoking the {@code get} method on the {@code Supplier}.
     *
     * @param <S> the type of the thread local's value
     * @param supplier the supplier to be used to determine the initial value
     * @return a new thread local variable
     * @throws NullPointerException if the specified supplier is null
     * @since 1.8
     */
    public static <S> ThreadLocal<S> withInitial(Supplier<? extends S> supplier) {
        return new SuppliedThreadLocal<>(supplier);
    }

    /**
     * Creates a thread local variable.
     * @see #withInitial(java.util.function.Supplier)
     */
    public ThreadLocal() {
    }

    /**
     * Returns the value in the current thread's copy of this
     * thread-local variable.  If the variable has no value for the
     * current thread, it is first initialized to the value returned
     * by an invocation of the {@link #initialValue} method.
     *
     * 返回当前线程的ThreadLocal变量的值的copy
     * （一开始调用set时放了什么值，返回的就是什么值；如果没放值，就会初始化为null，并返回null）
     *
     * @return the current thread's value of this thread-local
     */
    public T get() {
        // 获取当前线程
        Thread t = Thread.currentThread();
        // 获取线程内部维护的ThreadLocalMap
        ThreadLocalMap map = getMap(t);
        if (map != null) {
            // 如果map已经有了，则通过threadLocal来获取map里的Entry（key是ThreadLocal，
            // value是调用set(T value)时传来的）
            ThreadLocalMap.Entry e = map.getEntry(this);
            if (e != null) {
                @SuppressWarnings("unchecked")
                T result = (T)e.value;
                // 如果e不为空，则直接获取value并返回
                return result;
            }
        }
        // 如果map不存在，则进行初始化，并返回初始化的值
        return setInitialValue();
    }

    /**
     * Variant of set() to establish initialValue. Used instead
     * of set() in case user has overridden the set() method.
     *
     * @return the initial value
     */
    private T setInitialValue() {
        // 获取初始值
        T value = initialValue();
        // 底下的几行代码（从Thread到createMap部分）就相当于调用set(value);
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if (map != null)
            map.set(this, value);
        else
            createMap(t, value);
        // 返回设置的初始值
        return value;
    }

    /**
     * Sets the current thread's copy of this thread-local variable
     * to the specified value.  Most subclasses will have no need to
     * override this method, relying solely on the {@link #initialValue}
     * method to set the values of thread-locals.
     *
     * 将当前线程中ThreadLocal变量（比如是ti）对应的值（value）复制到
     * 当前线程的ThreadLocalMap（threadLocals）中
     * 实际上，是通过ti来操作线程的本地变量threadLocals
     *
     * （线程的本地变量：这个变量的范围是处于这个线程里的，从不同实例里取ti，只要是同一个线程，取到的值就是一样的；
     * 实例变量是依附于已经创建好的实例）
     *
     * @param value the value to be stored in the current thread's copy of
     *        this thread-local.
     */
    public void set(T value) {
        // 获取当前线程
        Thread t = Thread.currentThread();
        // 获取线程内部维护的ThreadLocalMap
        ThreadLocalMap map = getMap(t);
        if (map != null)
            // 如果map已经有了，就直接向map里放（key是ThreadLocal，value就是传过来的值）
            map.set(this, value);
        else
            // 如果map不存在，则根据t和value创建一个ThreadLocalMap
            createMap(t, value);
    }

    /**
     * Removes the current thread's value for this thread-local
     * variable.  If this thread-local variable is subsequently
     * {@linkplain #get read} by the current thread, its value will be
     * reinitialized by invoking its {@link #initialValue} method,
     * unless its value is {@linkplain #set set} by the current thread
     * in the interim.  This may result in multiple invocations of the
     * {@code initialValue} method in the current thread.
     *
     * @since 1.5
     */
     public void remove() {
         ThreadLocalMap m = getMap(Thread.currentThread());
         if (m != null)
             m.remove(this);
     }

    /**
     * Get the map associated with a ThreadLocal. Overridden in
     * InheritableThreadLocal.
     *
     * @param  t the current thread
     * @return the map
     */
    ThreadLocalMap getMap(Thread t) {
        return t.threadLocals;
    }

    /**
     * Create the map associated with a ThreadLocal. Overridden in
     * InheritableThreadLocal.
     *
     * @param t the current thread
     * @param firstValue value for the initial entry of the map
     */
    void createMap(Thread t, T firstValue) {
        // 创建一个ThreadLocalMap（key为ThreadLocal，value为传过来的值）
        // 同时把当前线程的threadLocals指向刚创建好的ThreadLocalMap
        t.threadLocals = new ThreadLocalMap(this, firstValue);
    }

    /**
     * Factory method to create map of inherited thread locals.
     * Designed to be called only from Thread constructor.
     *
     * @param  parentMap the map associated with parent thread
     * @return a map containing the parent's inheritable bindings
     */
    static ThreadLocalMap createInheritedMap(ThreadLocalMap parentMap) {
        return new ThreadLocalMap(parentMap);
    }

    /**
     * Method childValue is visibly defined in subclass
     * InheritableThreadLocal, but is internally defined here for the
     * sake of providing createInheritedMap factory method without
     * needing to subclass the map class in InheritableThreadLocal.
     * This technique is preferable to the alternative of embedding
     * instanceof tests in methods.
     */
    T childValue(T parentValue) {
        throw new UnsupportedOperationException();
    }

    /**
     * An extension of ThreadLocal that obtains its initial value from
     * the specified {@code Supplier}.
     */
    static final class SuppliedThreadLocal<T> extends ThreadLocal<T> {

        private final Supplier<? extends T> supplier;

        SuppliedThreadLocal(Supplier<? extends T> supplier) {
            this.supplier = Objects.requireNonNull(supplier);
        }

        @Override
        protected T initialValue() {
            return supplier.get();
        }
    }

    /**
     * ThreadLocalMap is a customized hash map suitable only for
     * maintaining thread local values. No operations are exported
     * outside of the ThreadLocal class. The class is package private to
     * allow declaration of fields in class Thread.  To help deal with
     * very large and long-lived usages, the hash table entries use
     * WeakReferences for keys. However, since reference queues are not
     * used, stale entries are guaranteed to be removed only when
     * the table starts running out of space.
     */
    static class ThreadLocalMap {

        /**
         * The entries in this hash map extend WeakReference, using
         * its main ref field as the key (which is always a
         * ThreadLocal object).  Note that null keys (i.e. entry.get()
         * == null) mean that the key is no longer referenced, so the
         * entry can be expunged from table.  Such entries are referred to
         * as "stale entries" in the code that follows.
         */
        static class Entry extends WeakReference<ThreadLocal<?>> {
            /** The value associated with this ThreadLocal. */
            // 和ThreadLocal进行关联的值
            Object value;

            Entry(ThreadLocal<?> k, Object v) {
                super(k);
                value = v;
            }
        }

        /**
         * The initial capacity -- MUST be a power of two.
         * 初始容量，必须为2的幂
         */
        private static final int INITIAL_CAPACITY = 16;

        /**
         * The table, resized as necessary.
         * table.length MUST always be a power of two.
         * Entry数组，大小必须为2的幂
         */
        private Entry[] table;

        /**
         * The number of entries in the table.
         * 表里entry的个数
         */
        private int size = 0;

        /**
         * The next size value at which to resize.
         * 重新分配表大小的阈值，默认为0（一般size>=threshold时，就会resize）
         */
        private int threshold; // Default to 0

        /**
         * Set the resize threshold to maintain at worst a 2/3 load factor.
         * 用负载因子2/3来维护resize时所需的阈值
         * （阈值=Entry数组容量*2/3）
         */
        private void setThreshold(int len) {
            threshold = len * 2 / 3;
        }

        /**
         * Increment i modulo len.
         * 环形意义的下一个索引（如果传入的i为len-1，已经到达数组的最后一个索引，
         * 则返回的下一个索引为0，也就是从头开始找）
         */
        private static int nextIndex(int i, int len) {
            return ((i + 1 < len) ? i + 1 : 0);
        }

        /**
         * Decrement i modulo len.
         * 环形意义的上一个索引（如果传入的i为0，已经到达数组的第一个索引，
         * 则返回的上一个索引为len-1，也就是从末尾开始找）
         */
        private static int prevIndex(int i, int len) {
            return ((i - 1 >= 0) ? i - 1 : len - 1);
        }

        /**
         * Construct a new map initially containing (firstKey, firstValue).
         * ThreadLocalMaps are constructed lazily, so we only create
         * one when we have at least one entry to put in it.
         *
         * 构造一个包含(firstKey,firstValue)的map
         * ThreadLocalMap是惰性构造的，所以当我们向map中放入至少一个entry时，我们只会创建一个ThreadLocalMap
         * （对于so这句话的理解：对于一个线程里维护的ThreadLocalMap来说，因为默认没有创建ThreadLocalMap的，只有当
         * 我们开始向这个线程里存放(threadlocal1,1),(threadlocal2,2)时，才会触发创建ThreadLocalMap
         * 你只调用threadlocal1.set(1)也是创建一个ThreadLocalMap，你连续调用threadlocal1.set(1)和threadlocal2.set(2)也是
         * 创建一个ThreadLocalMap；
         * 换句话说，就是该线程的ThreadLocalMap中放入一个元素时就会调用该构造方法，后续再向map里放元素时就不会调该构造方法了）
         */
        ThreadLocalMap(ThreadLocal<?> firstKey, Object firstValue) {
            // 初始化table数组
            table = new Entry[INITIAL_CAPACITY];
            // 用firstKey的threadLocalHashCode与初始大小16取模得到哈希值（这个算出来的在table中的存放位置就是哈希值）
            int i = firstKey.threadLocalHashCode & (INITIAL_CAPACITY - 1);
            // 初始化该节点
            table[i] = new Entry(firstKey, firstValue);
            // 设置节点表大小为1
            size = 1;
            // 设定扩容阈值
            setThreshold(INITIAL_CAPACITY);
        }

        /**
         * Construct a new map including all Inheritable ThreadLocals
         * from given parent map. Called only by createInheritedMap.
         *
         * @param parentMap the map associated with parent thread.
         */
        private ThreadLocalMap(ThreadLocalMap parentMap) {
            Entry[] parentTable = parentMap.table;
            int len = parentTable.length;
            setThreshold(len);
            table = new Entry[len];

            for (int j = 0; j < len; j++) {
                Entry e = parentTable[j];
                if (e != null) {
                    @SuppressWarnings("unchecked")
                    ThreadLocal<Object> key = (ThreadLocal<Object>) e.get();
                    if (key != null) {
                        Object value = key.childValue(e.value);
                        Entry c = new Entry(key, value);
                        int h = key.threadLocalHashCode & (len - 1);
                        while (table[h] != null)
                            h = nextIndex(h, len);
                        table[h] = c;
                        size++;
                    }
                }
            }
        }

        /**
         * Get the entry associated with key.  This method
         * itself handles only the fast path: a direct hit of existing
         * key. It otherwise relays to getEntryAfterMiss.  This is
         * designed to maximize performance for direct hits, in part
         * by making this method readily inlinable.
         *
         * 获取map中ThreadLocal对象关联的值
         * （这个方法会被ThreadLocal的get方法直接调用）
         *
         * @param  key the thread local object
         * @return the entry associated with key, or null if no such
         */
        private Entry getEntry(ThreadLocal<?> key) {
            // 根据key这个ThreadLocal的ID来获取索引，也就是哈希值
            int i = key.threadLocalHashCode & (table.length - 1);
            Entry e = table[i];
            // 对应的entry存在，且未失效，且弱引用指向的ThreadLocal就是key，则命中返回
            if (e != null && e.get() == key)
                return e;
            else
                // 因为用的是线性探测，所以往后找还是有可能能够找到目标Entry的
                return getEntryAfterMiss(key, i, e);
        }

        /**
         * Version of getEntry method for use when key is not found in
         * its direct hash slot.
         *
         * 调用getEntry未直接命中的时候调用此方法
         *
         * @param  key the thread local object
         * @param  i the table index for key's hash code
         * @param  e the entry at table[i]
         * @return the entry associated with key, or null if no such
         */
        private Entry getEntryAfterMiss(ThreadLocal<?> key, int i, Entry e) {
            Entry[] tab = table;
            int len = tab.length;

            // 基于线性探测法不断向后探测直到遇到空entry（其实就是直到遇到entry中ThreadLocal为null的）
            /**
             * 说明：比如len为16，从i=5开始向后找（为什么这里i=5还要再看一遍呢？
             * 因为此时对应的e.get()有可能是null，说明被清理掉了，需要调用expungeStaleEntry做下清理操作）
             * A.假设i=5不符合条件（且tab[i]!=null），接着向后找，找到i=6的位置
             * 情况1：发现i=6对应的tab[i]不为null且刚好是想要的位置（k==key），直接将相应的tab[i]返回，循环结束
             * 情况2：发现i=6对应的tab[i]为null（刚好对应ThreadLocal被回收了），则调用expungeStaleEntry(i)，
             * 将tab[i]置为null，之后取到的e为null，循环结束，返回null（表示没找到）
             * 情况3：发现i=6对应的tab[i]不为null，不是想要的位置（k!=key），那么继续向后找
             * B.i=5不符合条件（且tab[i]=null），不用再找了（不会进while循环），直接返回null
             *
             * 为什么是这么找呢？因为线性探测法在确定放ThreadLocal的位置i（ThreadLocalMap.set()），是通过i=key.threadLocalHashCode&(len-1)得出初始位置，
             * 记tab[i]=e，如果满足e==null（该索引上没有Entry）或者e!=null&&k=key（该索引上有Entry且Entry的key和传入的ThreadLocal一致），
             * 就把含有ThreadLocal的Entry放到该索引；否则一直向后找，直到找到满足条件的为止
             *
             * 从这个放ThreadLocal的方式，可以看出：如果计算的位置i上为null，说明这个位置没放过Entry或者放了之后被回收了，没有必要再去找了；
             * 如果计算的位置上有Entry，只是key对不上而已，那就向后找（线性探测法就是在基于算出来的哈希确定位置，如果没冲突，就放到该位置；如果冲突了就是该位置上已经有元素了，
             * 就接着找没有放元素的位置）
             */
            while (e != null) {
                ThreadLocal<?> k = e.get();
                // 找到目标
                if (k == key)
                    return e;
                if (k == null)
                    // 该entry对应的ThreadLocal已经被回收，调用expungeStaleEntry来清理无效的entry
                    expungeStaleEntry(i);
                else
                    // 环形意义下获取下一个索引
                    i = nextIndex(i, len);
                e = tab[i];
            }
            return null;
        }

        /**
         * Set the value associated with key.
         * 将key和value进行关联
         *
         * @param key the thread local object
         * @param value the value to be set
         *
         * 简单总结下set方法可能会有的情况：
         * （1）探测过程中slot都不无效，并且顺利找到key所在的slot，直接替换即可
         * （2）探测过程中发现有无效slot，调用replaceStaleEntry，效果是最终一定会把key和value放在这个slot，并且会尽可能清理无效slot
         *     a.在replaceStaleEntry过程中，找到了key，则做一个swap把它放到那个无效slot中，value置为新值
         *     b.在replaceStaleEntry过程中，没有找到key，直接在无效slot原地放entry
         * （3）探测过程中没有发现key，则在连续段末尾的后一个空位置放上entry，这也是线性探测法的一部分
         */
        private void set(ThreadLocal<?> key, Object value) {

            // We don't use a fast path as with get() because it is at
            // least as common to use set() to create new entries as
            // it is to replace existing ones, in which case, a fast
            // path would fail more often than not.

            Entry[] tab = table;
            int len = tab.length;
            int i = key.threadLocalHashCode & (len-1);

            // 线性探测
            for (Entry e = tab[i];
                 e != null;
                 e = tab[i = nextIndex(i, len)]) {
                ThreadLocal<?> k = e.get();

                // 找到对应的entry
                if (k == key) {
                    e.value = value;
                    return;
                }

                // 替换失效的entry
                if (k == null) {
                    replaceStaleEntry(key, value, i);
                    return;
                }
            }

            // 未发现key，则在找到的空位置上放上entry
            tab[i] = new Entry(key, value);
            int sz = ++size;
            // 放完后，做一次启发式清理，如果没有清理出去key，且当前table大小已经超过阈值了，
            // 则做一次rehash，rehash函数会调用一次全量清理slot方法（expungeStaleEntries），
            // 如果完了之后table大小超过了threshold-threshold/4，则将table容量扩充至原来的2倍
            if (!cleanSomeSlots(i, sz) && sz >= threshold)
                rehash();
        }

        /**
         * Remove the entry for key.
         * 从map中删除ThreadLocal
         */
        private void remove(ThreadLocal<?> key) {
            Entry[] tab = table;
            int len = tab.length;
            int i = key.threadLocalHashCode & (len-1);
            for (Entry e = tab[i];
                 e != null;
                 e = tab[i = nextIndex(i, len)]) {
                if (e.get() == key) {
                    // 显式断开弱引用
                    e.clear();
                    // 进行段清理
                    expungeStaleEntry(i);
                    return;
                }
            }
        }

        /**
         * Replace a stale entry encountered during a set operation
         * with an entry for the specified key.  The value passed in
         * the value parameter is stored in the entry, whether or not
         * an entry already exists for the specified key.
         *
         * As a side effect, this method expunges all stale entries in the
         * "run" containing the stale entry.  (A run is a sequence of entries
         * between two null slots.)
         *
         * @param  key the key
         * @param  value the value to be associated with key
         * @param  staleSlot index of the first stale entry encountered while
         *         searching for key.
         */
        private void replaceStaleEntry(ThreadLocal<?> key, Object value,
                                       int staleSlot) {
            Entry[] tab = table;
            int len = tab.length;
            Entry e;

            // Back up to check for prior stale entry in current run.
            // We clean out whole runs at a time to avoid continual
            // incremental rehashing due to garbage collector freeing
            // up refs in bunches (i.e., whenever the collector runs).
            // 向前扫描，查找最前的一个无效slot
            int slotToExpunge = staleSlot;
            for (int i = prevIndex(staleSlot, len);
                 (e = tab[i]) != null;
                 i = prevIndex(i, len))
                if (e.get() == null)
                    slotToExpunge = i;

            // Find either the key or trailing null slot of run, whichever
            // occurs first
            // 向后遍历table
            for (int i = nextIndex(staleSlot, len);
                 (e = tab[i]) != null;
                 i = nextIndex(i, len)) {
                ThreadLocal<?> k = e.get();

                // If we find key, then we need to swap it
                // with the stale entry to maintain hash table order.
                // The newly stale slot, or any other stale slot
                // encountered above it, can then be sent to expungeStaleEntry
                // to remove or rehash all of the other entries in run.
                // 找到了key，将其与无效的slot交换
                if (k == key) {
                    // 更新对应slot的value值
                    e.value = value;

                    tab[i] = tab[staleSlot];
                    tab[staleSlot] = e;

                    // Start expunge at preceding stale entry if it exists
                    /**
                     * 如果在整个扫描过程中（包括函数一开始的向前扫描与其后的向后扫描）
                     * 找到了之前的无效slot则以那个位置作为清理的起点，
                     * 否则，则以当前的i作为清理起点（下面if判断的情况）
                     */
                    if (slotToExpunge == staleSlot)
                        slotToExpunge = i;
                    // 从slotToExpunge开始做一次连续段的清理，再做一次启发式清理
                    cleanSomeSlots(expungeStaleEntry(slotToExpunge), len);
                    return;
                }

                // If we didn't find stale entry on backward scan, the
                // first stale entry seen while scanning for key is the
                // first still present in the run.
                // 如果当前的slot已经无效，并且向前扫描过程中没有无效slot，则更新slotToExpunge为当前位置
                if (k == null && slotToExpunge == staleSlot)
                    slotToExpunge = i;
            }

            // If key not found, put new entry in stale slot
            // 如果key在table中不存在，则在原地放一个即可
            tab[staleSlot].value = null;
            tab[staleSlot] = new Entry(key, value);

            // If there are any other stale entries in run, expunge them
            // 在探测过程中如果发现任何无效slot，则做一次清理（连续段清理+启发式清理）
            if (slotToExpunge != staleSlot)
                cleanSomeSlots(expungeStaleEntry(slotToExpunge), len);
        }

        /**
         * Expunge a stale entry by rehashing any possibly colliding entries
         * lying between staleSlot and the next null slot.  This also expunges
         * any other stale entries encountered before the trailing null.  See
         * Knuth, Section 6.4
         *
         * 这个函数是ThreadLocal中的核心清理函数，它做的事情很简单：
         * 就是从staleSlot开始遍历，将无效（弱引用指向对象被回收）slot清理，即对应entry中的value置为null，
         * 将指向这个entry的table[i]置为null，直到扫到空entry。另外，在此过程中还会对非空的entry做rehash
         * 可以说这个函数的作用就是从staleSlot开始清理连续段中的slot（断开强引用，rehash slot）
         *
         * @param staleSlot index of slot known to have null key
         * @return the index of the next null slot after staleSlot
         * (all between staleSlot and this slot will have been checked
         * for expunging).
         */
        private int expungeStaleEntry(int staleSlot) {
            Entry[] tab = table;
            int len = tab.length;

            // expunge entry at staleSlot
            // 因为entry对应的ThreadLocal已经被回收，value设为null，显式断开强引用
            tab[staleSlot].value = null;
            // 显式设置该entry为null，以便垃圾回收
            tab[staleSlot] = null;
            size--;

            // Rehash until we encounter null
            Entry e;
            int i;
            for (i = nextIndex(staleSlot, len);
                 (e = tab[i]) != null;
                 i = nextIndex(i, len)) {
                ThreadLocal<?> k = e.get();
                // 清理对应ThreadLocal已经被回收的entry
                if (k == null) {
                    e.value = null;
                    tab[i] = null;
                    size--;
                } else {
                    /**
                     * 对于还没有被回收的情况，需要做一次rehash
                     *
                     * 如果对应的ThreadLocal的ID对len取模出来的索引h不为当前位置i，
                     * 则从h向后线性探测到第一个空的slot，把当前的entry给挪过去
                     */
                    int h = k.threadLocalHashCode & (len - 1);
                    if (h != i) {
                        tab[i] = null;

                        // Unlike Knuth 6.4 Algorithm R, we must scan until
                        // null because multiple entries could have been stale.
                        while (tab[h] != null)
                            h = nextIndex(h, len);
                        tab[h] = e;
                    }
                }
            }
            return i;
        }

        /**
         * Heuristically scan some cells looking for stale entries.
         * This is invoked when either a new element is added, or
         * another stale one has been expunged. It performs a
         * logarithmic number of scans, as a balance between no
         * scanning (fast but retains garbage) and a number of scans
         * proportional to number of elements, that would find all
         * garbage but would cause some insertions to take O(n) time.
         * 启发式地清理slot
         *
         * @param i a position known NOT to hold a stale entry. The
         * scan starts at the element after i.
         * i对应于一个已经知道没有放无效entry（entry本身不为空，但指向的ThreadLocal被回收）的位置
         * 扫描将从i之后的位置进行
         *
         * @param n scan control: {@code log2(n)} cells are scanned,
         * unless a stale entry is found, in which case
         * {@code log2(table.length)-1} additional cells are scanned.
         * When called from insertions, this parameter is the number
         * of elements, but when from replaceStaleEntry, it is the
         * table length. (Note: all this could be changed to be either
         * more or less aggressive by weighting n instead of just
         * using straight log n. But this version is simple, fast, and
         * seems to work well.)
         * n是用于控制扫描次数的
         * 正常情况下如果log2(n)次扫描没有发现无效slot，函数就结束了
         * 但是如果发现了无效的slot，则将n置为table的长度len，做一次连续段的清理
         * 再从下一个空的slot开始继续扫描
         *
         * 这个函数有两处地方会被调用，一处是插入的时候（ThreadLocalMap.set）可能会被调用，
         * 另外是在替换无效slot的时候（ThreadLocalMap.expungeStaleEntry）可能会被调用。
         * 区别是前者传入的n为table的元素个数，后者为table的容量
         *
         * @return true if any stale entries have been removed.
         * 是否有无效entry被移除，有则返回true
         */
        private boolean cleanSomeSlots(int i, int n) {
            boolean removed = false;
            Entry[] tab = table;
            int len = tab.length;
            do {
                // 从参数定义上看，可以知道i不会是一个无效slot，所以从下一个开始判断
                i = nextIndex(i, len);
                Entry e = tab[i];
                if (e != null && e.get() == null) {
                    // 扩大扫描控制因子
                    n = len;
                    removed = true;
                    // 清理一个连续段
                    i = expungeStaleEntry(i);
                }
            } while ( (n >>>= 1) != 0);
            return removed;
        }

        /**
         * Re-pack and/or re-size the table. First scan the entire
         * table removing stale entries. If this doesn't sufficiently
         * shrink the size of the table, double the table size.
         */
        private void rehash() {
            // 做一次全量清理
            expungeStaleEntries();

            // Use lower threshold for doubling to avoid hysteresis
            /**
             * 因为做了一次清理，所以size很可能会变小
             * ThreadLocalMap这里的实现是调低阈值来判断是否需要扩容
             * threshold默认为len*2/3，所以这里的threshold-threshold/4相当于len/2
             */
            if (size >= threshold - threshold / 4)
                resize();
        }

        /**
         * Double the capacity of the table.
         * 扩容，因为需要保证table的容量len为2的幂，所以扩容即扩大2倍
         */
        private void resize() {
            Entry[] oldTab = table;
            int oldLen = oldTab.length;
            int newLen = oldLen * 2;
            Entry[] newTab = new Entry[newLen];
            int count = 0;

            for (int j = 0; j < oldLen; ++j) {
                Entry e = oldTab[j];
                if (e != null) {
                    ThreadLocal<?> k = e.get();
                    if (k == null) {
                        e.value = null; // Help the GC
                    } else {
                        // 线性探测来存放Entry
                        int h = k.threadLocalHashCode & (newLen - 1);
                        while (newTab[h] != null)
                            h = nextIndex(h, newLen);
                        newTab[h] = e;
                        count++;
                    }
                }
            }

            setThreshold(newLen);
            size = count;
            table = newTab;
        }

        /**
         * Expunge all stale entries in the table.
         * 做一次全量清理
         */
        private void expungeStaleEntries() {
            Entry[] tab = table;
            int len = tab.length;
            for (int j = 0; j < len; j++) {
                Entry e = tab[j];
                if (e != null && e.get() == null)
                /**
                 * 个人觉得这里可以取返回值，如果大于j的话取了用，这样也是可行的。即
                 * j = expungeStaleEntry(j);
                 * 因为expungeStaleEntry执行过程中是把连续段内所有无效slot都清理了一遍
                 */
                    expungeStaleEntry(j);
            }
        }
    }
}
