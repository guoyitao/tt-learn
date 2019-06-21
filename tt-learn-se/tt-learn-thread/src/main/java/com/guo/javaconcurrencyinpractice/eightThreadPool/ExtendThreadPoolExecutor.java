package com.guo.javaconcurrencyinpractice.eightThreadPool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

/**
 * @Description: 扩展ThreadPoolExecutor 统计每个线程的处理时间
 * @Author: guo
 * @CreateDate: 2019/4/12
 * @UpdateUser:
 */
public class ExtendThreadPoolExecutor extends ThreadPoolExecutor {

    private final ThreadLocal<Long> startTime = new ThreadLocal<>();
    private final Logger logger = Logger.getLogger("ExtendThreadPoolExecutor");
    private final AtomicLong numTask = new AtomicLong();
    private final AtomicLong totalTime = new AtomicLong();


    public ExtendThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public ExtendThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public ExtendThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public ExtendThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }


    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        System.out.println(String.format("beforeExecute  Thread %s: start %s",t,r));
        logger.fine(String.format("beforeExecute  Thread %s: start %s",t,r));

        startTime.set(System.nanoTime());
//        throw new RuntimeException();  如果这里出现异常下面的周期方法都不能执行，而且任务也不能运行和提交
    }

    //处理error以外的异常都会运行afterExecute
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        try {
            long endTime = System.nanoTime();
            long taskTime = endTime-startTime.get();
            numTask.incrementAndGet();
            totalTime.addAndGet(taskTime);
            System.out.println(String.format("afterExecute  Thread %s : end %s ，time %dns",t,r,taskTime));
            logger.fine(String.format("afterExecute  Thread %s : end %s ，time %dns",t,r,taskTime));
        } finally {
            super.afterExecute(r, t);
        }

    }
    //shutdown的时候会调用
    @Override
    protected void terminated() {
        try {
            System.out.println(String.format("terminated avg time=%dns",totalTime.get()/numTask.get()));
            logger.info(String.format("terminated avg time=%dns",totalTime.get()/numTask.get()));
        } finally {
            super.terminated();
        }
    }

    public static void main(String[] args) {
        ExtendThreadPoolExecutor extendThreadPoolExecutor = new ExtendThreadPoolExecutor(3,10,0L,TimeUnit.SECONDS,new LinkedBlockingQueue<>());
        ExecutorService executorService = Executors.unconfigurableExecutorService(extendThreadPoolExecutor);

//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("runing ..........");
//            }
//        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
               throw  new RuntimeException("MyhRuntimeException");
            }
        });
        executorService.isTerminated();


    }
}
