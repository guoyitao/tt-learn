package com.guo.threadreadbattcn;


import java.util.stream.Stream;

/**
 * wait和sleep的区别
 *
 * 简单来说，wait（）是一个用于线程同步的实例方法。它可以在任何对象上调用，因为它定义在java.lang.Object上， 但只能从同步块中调用。它释放对象上的锁，以便另一个线程可以跳入并获取锁。
 * 另一方面，Thread.sleep（）是可以从任何上下文调用的静态方法。Thread.sleep（）暂停当前​​线程，不释放任何锁。
 *
 * 主要区别：{
 *   sleep（）线程控制自身流程。wait（）用来线程间通信，使拥有该对象锁的线程等待直到指定时间或notify（）。
 *
 *   wait()会释放锁和监视器，sleep()不释放任何锁或监视器等。
 *   wait()用于线程间通信，而sleep()用于在执行时引入暂停
 *
 *   适用区域，wait只能放在同步语句块中才有意义。
 * }
 *
 *
 * @Description:
 * @Author: guo
 * @CreateDate: 2019/4/3
 * @UpdateUser:
 */
public class DifferenceOfWaitAndSleep {
    private static final byte[] LOCK = new byte[0];

    static void method1() {
        synchronized (LOCK) {
            try {
                System.out.println("[" + Thread.currentThread().getName() + "] begin sleep ...");

                Thread.sleep(5_000);
                System.out.println("[" + Thread.currentThread().getName() + "] end sleep ...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static void method2() {
        synchronized (LOCK) {
            try {
                System.out.println("[" + Thread.currentThread().getName() + "] begin wait ...");

                LOCK.wait();
                System.out.println("[" + Thread.currentThread().getName() + "] end wait ...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static void testsleep(){
        Stream.of("T1", "T2").forEach(name -> new Thread(DifferenceOfWaitAndSleep::method1, name).start());
    }

    /*
    * 运行此示例会立即输出T1/T2 begin wait ... 但永远不会输出T1/T2 end wait ...，因为没有线程调用LOCK.notify/notifyAll将它们唤醒
    * */
    static void testWait(){
        Stream.of("T1", "T2").forEach(name -> new Thread(DifferenceOfWaitAndSleep::method2, name).start());
    }

    public static void main(String[] args) {
        testWait();
    }

}
