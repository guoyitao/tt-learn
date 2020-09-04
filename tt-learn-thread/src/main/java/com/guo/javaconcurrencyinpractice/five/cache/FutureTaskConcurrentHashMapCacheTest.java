package com.guo.javaconcurrencyinpractice.five.cache;


import com.guo.javaconcurrencyinpractice.fiveblockqueuefutruesemaphore.FutureTaskTest;

import java.util.Map;
import java.util.concurrent.*;

class Memoizer3<A, V> implements Computable<A, V> {
    public final ExecutorService exec = Executors.newFixedThreadPool(10);
    private final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A, V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        Future<V> result = cache.get(arg);
        //复合操作若没有就添加 start
        if (result == null) {
            result = new FutureTask<>(() -> {
                return c.compute(arg);
            });
            cache.put(arg,result);
            //复合操作end
            ((FutureTask<V>) result).run();
        }


        try {
            return result.get();
        } catch (ExecutionException e) {
            throw  FutureTaskTest.launderThrowable(e);
        }
    }
}

/**
 * 使用FutureTask<V>作为缓存map的value，来查询有没有同样正在计算的
 *
 * 问题：降低了触发重复计算的几率，因为使用了Map的非原子复合操作
 *
 * @Description:
 * @Author: guo
 * @CreateDate: 2019/4/7
 * @UpdateUser:
 */
public class FutureTaskConcurrentHashMapCacheTest {

    public static void main(String[] args) {
        Memoizer3 memoizer3 = new Memoizer3(new ExpensiveFunction());

        for (int i = 0; i < 1000; i++) {
            memoizer3.exec.execute(()->{
                try {
                    memoizer3.compute("2222");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
