package com.guo.javaconcurrencyinpractice.eightThreadPool;

import java.util.concurrent.*;

class BoundExecutor{
    private final Executor exec;
    private final Semaphore semaphore;

    public BoundExecutor(Executor exec, Semaphore semaphore) {
        this.exec = exec;
        this.semaphore = semaphore;
    }

    public void submitTask(final Runnable task) throws InterruptedException {
        //如果没有许可剩余就阻塞，直到有许可
        //许可-1
        semaphore.acquire();
        try {
            exec.execute(()->{
                try {
                    task.run();
                } finally {
                    semaphore.release();
                }
            });
        } catch (RejectedExecutionException e) {
            semaphore.release();
        }
    }
}

public class TestRejectedExecutionHandler {

    public  ThreadPoolExecutor executor;

    public TestRejectedExecutionHandler() {
        executor  =new ThreadPoolExecutor(3,10 ,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<>(30));
        //拒绝执行器
        //拒绝了之后会在调用者线程执行任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
    }
    /** 使用Semaphore 来控制任务提交速率
     * @author guoyitao
     * @date 2019/4/12 17:54
     */
    public static void main(String[] args) {
        TestRejectedExecutionHandler testRejectedExecutionHandler = new TestRejectedExecutionHandler();
        BoundExecutor boundExecutor = new BoundExecutor(testRejectedExecutionHandler.executor,new Semaphore(100));

        for (int i = 0; i < 1000; i++) {
            System.out.println("will exec" + i);
            try {
                boundExecutor.submitTask(()->{
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
