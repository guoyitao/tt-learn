package com.guo.mashibing;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    ReentrantLock reentrantLock = new ReentrantLock();

    void m1() {


        try {
            System.out.printf("m1 start");
            reentrantLock.lock();
            TimeUnit.SECONDS.sleep(1999);
            System.out.printf("m1 end");
        } catch (InterruptedException e) {
            System.out.printf("m1 被打断");
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }

    }

    void m2() {

        boolean islock = true;
        try {
            reentrantLock.lockInterruptibly();
            System.out.printf("m2 start");
            TimeUnit.SECONDS.sleep(2);
            System.out.printf("m2 end");
        } catch (InterruptedException e) {
            System.out.printf("m2 打断");
            islock = false;
            e.printStackTrace();
        }finally {
            if (islock) {
                System.out.println(reentrantLock.isLocked());
                reentrantLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        LockTest lockTest = new LockTest();
        new Thread(()->{lockTest.m1();}).start();
        Thread thread = new Thread(() -> {
            lockTest.m2();
        });
        thread.start();

        thread.interrupt();

    }
}
