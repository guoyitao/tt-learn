package com.guo.javaconcurrencyinpractice.five.cache;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

interface Computable<T,X>{
    X compute(T arg) throws InterruptedException;
}
//计算过程性
class ExpensiveFunction implements Computable<String, BigInteger>{

    @Override
    public BigInteger compute(String arg) {
        try {
            System.out.println("计算了");
            Thread.sleep(new Long(arg));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new BigInteger(arg);
    }
}

class Meoizer<T,X> implements Computable<T,X>{

    private final Map<T,X> cache = new HashMap<>();
    private final Computable<T,X> c;

    public Meoizer(Computable<T,X> computable) {
        this.c = computable;
    }

    @Override
    public synchronized X compute(T arg) throws InterruptedException {
        X result = cache.get(arg);
        if (result == null){
            result = c.compute(arg);
            cache.put(arg,result);
        }
        return result;
    }
}

/**
 * HashMap版本
 * 存在问题：{
 *     每次只要一个线程能够执行compute（）,其他线程都要等待会被阻塞可能阻塞会很久
 * }
 *
 * @Description:
 * @Author: guo
 * @CreateDate: 2019/4/7
 * @UpdateUser:
 */
public class HashMapCacheTest {

    public static void main(String[] args) {
        Meoizer<String,BigInteger> meoizer = new Meoizer<>(new ExpensiveFunction());


        try {
            BigInteger compute = meoizer.compute("2243");
            BigInteger compute2 = meoizer.compute("2243");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
