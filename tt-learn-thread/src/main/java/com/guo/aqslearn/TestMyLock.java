package com.guo.aqslearn;

import java.util.concurrent.CountDownLatch;

public class TestMyLock {

    private static int value = 0;
    private static int count = 100;
    private static MyLock lock = new MyLock();
    private static CountDownLatch countDownLatch = new CountDownLatch(count);
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
                countDownLatch.countDown();
            });
        }

        //run
        for (int i = 0; i <threads.length ; i++) {
            threads[i].start();
        }

        //等下线程全部执行完
        countDownLatch.await();

        System.out.println(value);

    }
}
