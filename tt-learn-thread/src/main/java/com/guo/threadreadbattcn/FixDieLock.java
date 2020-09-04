package com.guo.threadreadbattcn;


import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
//TODO 信号量demo避免死锁
class TryLock{
    public static final Semaphore SEMAPHORE_LOCK = new Semaphore(1);
    public static final Semaphore SEMAPHORE_OTHER = new Semaphore(1);
}

class DeadLock1 {

    private final byte[] LOCK = new byte[0];
    private DeadLock2 deadLock2 = new DeadLock2();

    public DeadLock1(DeadLock2 deadLock2) {
        this.deadLock2 = deadLock2;
    }

    public void method1() {
        try {
            if (TryLock.SEMAPHORE_LOCK.tryAcquire(1, TimeUnit.SECONDS)) {
                synchronized (LOCK) {
                    System.out.println("method1");
                    deadLock2.lock1();
                }
            } else {
                System.out.println("method1 获取锁失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void method2() {
        try {
            if (TryLock.SEMAPHORE_LOCK.tryAcquire(1, TimeUnit.SECONDS)) {
                synchronized (LOCK) {
                    System.out.println("method2");
                }
            } else {
                System.out.println("method2 获取锁失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class DeadLock2 {

    private final byte[] LOCK = new byte[0];
    private DeadLock1 deadLock1;

    public void setDeadLock(DeadLock1 deadLock1) {
        this.deadLock1 = deadLock1;
    }

    public void lock1() {
        try {
            if (TryLock.SEMAPHORE_OTHER.tryAcquire(1, TimeUnit.SECONDS)) {
                synchronized (LOCK) {
                    System.out.println("lock1=========");
                }
            } else {
                System.out.println("lock1 获取锁失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void lock2() {
        try {
            if (TryLock.SEMAPHORE_OTHER.tryAcquire(1, TimeUnit.SECONDS)) {
                synchronized (LOCK) {
                    System.out.println("lock2=========");
                    deadLock1.method2();
                }
            } else {
                System.out.println("lock1 获取锁失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



public class FixDieLock {

    public static void main(String[] args) {
        DeadLock2 deadLock2 = new DeadLock2();
        DeadLock1 deadLock1 = new DeadLock1(deadLock2);

        deadLock2.setDeadLock(deadLock1);

        new Thread(() -> {
            while (true) {
                deadLock1.method1();
                TryLock.SEMAPHORE_OTHER.release();

            }
        }).start();
        new Thread(() -> {
            while (true) {
                deadLock2.lock2();
                TryLock.SEMAPHORE_LOCK.release();
            }
        }).start();

    }
}
