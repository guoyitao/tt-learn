package com.guo.aqslearn;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

//测试 MyLock2
public class TestLock1 {

    private static int value = 0;
    private static int count = 100;
    private static Lock lock = new MyLock2();
    private static Thread[] threads = new Thread[count];

    public static void main(String[] args) throws InterruptedException {
        //init
        for (int i = 0; i <threads.length ; i++) {
            threads[i] = new Thread(()->{

                try {
                    lock.lock();
                    for (int j = 0; j < 100 ; j++) {
                        value++;
                    }
                } finally {
                    lock.unlock();
                }
            });
        }

        //run
        for (int i = 0; i <threads.length ; i++) {
            threads[i].start();
        }

        //等下线程全部执行完
        for (int i = 0; i <threads.length ; i++) {
            threads[i].join();
        }

        System.out.println(value);

    }
}
