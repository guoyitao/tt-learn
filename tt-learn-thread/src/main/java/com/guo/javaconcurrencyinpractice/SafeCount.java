package com.guo.javaconcurrencyinpractice;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * 这样的操作是不原子的，因为anInt++看起来其实只要一步操作，其实背后有很多步，
 * 我们可以把它看做  “读取->修改->写入” 的操作顺序,并且它的结果依赖于之前的据结果，
 * 这样的线程不安全的，如果并发量达到一定的数就会看见问题，针对这种问题java有java.util.concurrent.atomic这样的解决方案，
 * 可以提供原子的操作
 * @Description:
 * @Author: guo
 * @CreateDate: 2019/4/3
 * @UpdateUser:
 */
public class SafeCount {
    private static AtomicInteger atomicInteger = new AtomicInteger(1);


    public static void main(String[] args) {
        new Thread(atomicInteger::getAndIncrement).start();
        new Thread(atomicInteger::getAndIncrement).start();
        new Thread(atomicInteger::getAndIncrement).start();
    }

    private static int anInt = 1;

//    public static void main(String[] args) {
//        new Thread(()->{anInt++;}).start();
//        new Thread(()->{anInt++;}).start();
//        new Thread(()->{anInt++;}).start();
//    }
}
