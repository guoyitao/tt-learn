package com.guo.javaconcurrencyinpractice.five.cache;

import com.guo.javaconcurrencyinpractice.fiveblockqueuefutruesemaphore.FutureTaskTest;

import java.util.Map;
import java.util.concurrent.*;

class Memoizer4<A, V> implements Computable<A, V> {
    public final ExecutorService exec = Executors.newFixedThreadPool(10);
    private final ConcurrentMap<A, Future<V>> cache = new ConcurrentHashMap<>(); //优化的修改！！！！！！！！！
    private final Computable<A, V> c;

    public Memoizer4(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public V compute(A arg) throws InterruptedException {
        while (true){
            Future<V> f = cache.get(arg);
            if (f == null){
                FutureTask ft = new FutureTask(()->{
                   return c.compute(arg);
                });
                f = cache.putIfAbsent(arg, ft);
                if (f == null){
                    f = ft;
                    ft.run();
                }
            }

            try {
                return f.get();
            } catch (CancellationException e) {
                cache.remove(arg,f);
            } catch (ExecutionException e){
                cache.remove(arg,f);
                throw FutureTaskTest.launderThrowable(e.getCause());
            }
        }
    }
}

/**
 * 解决重复计算和并发，但是没有解决缓存定期清理
 *
 * @Description:
 * @Author: guo
 * @CreateDate: 2019/4/7
 * @UpdateUser:
 */
public class GoodCacheTest {

    public static void main(String[] args) {
        Memoizer4 memoizer4 = new Memoizer4(new ExpensiveFunction());

        for (int i = 0; i < 1000; i++) {
            memoizer4.exec.execute(()->{
                try {
                    memoizer4.compute("2222");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
