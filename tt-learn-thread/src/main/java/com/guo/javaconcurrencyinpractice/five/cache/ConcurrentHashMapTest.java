package com.guo.javaconcurrencyinpractice.five.cache;


import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Memoizer2<A,V> implements Computable<A,V>{
    public final ExecutorService exec = Executors.newFixedThreadPool(10);
    private final Map<A,V> cache = new ConcurrentHashMap<>();
    private final Computable<A,V> c;

    public Memoizer2(Computable<A, V> computable) {
        this.c = computable;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        V result = cache.get(arg);
        if (result== null){
            result = c.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }
}

/**ConcurrentHashMap比HashMap版本有更好的并发性能
 *
 * 存在问题:{
 *     但多个线程同时调用的时候可能出现重复计算的问题
 * }
 *
 * @Description:
 * @Author: guo
 * @CreateDate: 2019/4/7
 * @UpdateUser:
 */
public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        Memoizer2<String, BigInteger> memoizer2= new Memoizer2<>(new ExpensiveFunction());

        for (int i = 0; i < 100; i++) {
            memoizer2.exec.submit(()->{
                try {
                    memoizer2.compute("123456");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }
}
