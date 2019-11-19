package kittycoder.java.lang;

/**
 * Created by shucheng on 2019-11-16 下午 22:37
 *
 * ThreadLocalMap使用“开放寻址法”中的的“线性探查法”解决散列冲突问题
 * （散列性检查：https://www.cnblogs.com/dennyzhangdd/p/7978455.html
 * 线性探查法：https://www.cnblogs.com/skywang12345/p/3311909.html）
 *
 * 这部分的原因目前还没完全了解，以后再花时间慢慢理下
 * 没理解的部分：不知道该如何校验一个hash算法的散列性
 *
 * 详细说明：
 * 验证的是能否：将n个连续的数散列到[0,n]上
 * 使用的算法是：1.先算一个hashCode（做hash映射） 2.然后将hashCode和(n-1)取余，进行散列
 * 2019-11-16
 * 疑问说明：（1）这里有必要用算法吗？直接用i（i∈[0,n-1]），和(n-1)取余不就完了吗？
 * （2）如果真的要验证散列性，是不是用一堆随机生成的不同整数（整数的数量为(n-1)）更能说明问题
 */
public class ThreadLocalMagicCodeTest {

    //ThreadLocal中定义的hash魔数
    private static final int HASH_INCREMENT = 0x61c88647;//ThreadLocal中定义的hash魔数

    public static void main(String[] args) {
        hashCode(32);
        hashCode(64);

        /*for (int i = 17; i <= 30; i++) {
            System.out.println(myHashCode(i, 16));
        }*/
    }

    private static int myHashCode(int i, int length) {
        int hashCode;
        hashCode = i*HASH_INCREMENT+HASH_INCREMENT;
        return hashCode & (length-1);
    }

    /**
     *
     * @Description 寻找散列下标（对应数组下标）
     * @param length table长度
     * @author diandian.zhang
     * @date 2017年12月6日上午10:36:53
     * @since JDK1.8
     */
    private static void hashCode(Integer length){
        int hashCode = 0;
        for(int i=0;i<length;i++){
                hashCode = i*HASH_INCREMENT+HASH_INCREMENT;//每次递增HASH_INCREMENT
                System.out.print(hashCode & (length-1));//求散列下标，算法公式
                System.out.print(" ");
            }
        System.out.println();
    }
}
