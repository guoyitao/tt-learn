package com.guo.mashibing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestExecutor {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            threadPool.execute(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        //等待线程都执行完了再关闭
        threadPool.shutdown();
        System.out.println("线程池的任务都执行完了吗？" + threadPool.isTerminated());
        System.out.println("线程池关闭了吗？（正在关闭）" + threadPool.isShutdown());
        System.out.println(threadPool);

        TimeUnit.SECONDS.sleep(5);
        System.out.println("线程池还有没有执行完的任务吗？" + threadPool.isTerminated());
        System.out.println("线程池关闭了吗？" + threadPool.isShutdown());
        System.out.println(threadPool);
    }
}
