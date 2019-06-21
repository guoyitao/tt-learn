package com.guo.javaconcurrencyinpractice.fiveblockqueuefutruesemaphore;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch闭锁（一个控制开关的门锁）
 *
 * timeTask(int nThread,Runnable task)
 *
 * 重复运行nThread个task并计算一共用了多久
 *
 * 过程：
 * 所有线程先全部启动完毕，然后开始运行task，等到全部task运行完毕然后，计算时间结束方法
 *
 * @Description:
 * @Author: guo
 * @CreateDate: 2019/4/6
 * @UpdateUser:
 */
public class CountDownLatchTest  {

    public static void main(String[] args) {
        try {
            System.out.println(timeTask(10,()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static long timeTask(int nThread,Runnable task) throws InterruptedException {
        CountDownLatch startGate = new CountDownLatch(1);
        CountDownLatch endGate = new CountDownLatch(nThread);

        for (int i = 0; i < nThread; i++) {
            Thread thread = new Thread(()->{
                try {
                    startGate.await();
                    try {
                        task.run();
                    } finally {
                     endGate.countDown();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
        }

        long start = System.nanoTime();
        startGate.countDown();//开门
        System.out.println("开门");
        endGate.await();//等到所有线程都跑完endGate.countDown(); 了之后
        System.out.println("任务完成");
        long end = System.nanoTime();
        return end-start;
    }
}
