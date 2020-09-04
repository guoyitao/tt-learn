package com.guo.javaconcurrencyinpractice.eightThreadPool.threadfactory;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * @Description: 自定义的ThreadFactory
 * @Author: guo
 * @CreateDate: 2019/4/12
 * @UpdateUser:
 */
public class MyThreadFactory  implements ThreadFactory {

    private final String poolName;

    public MyThreadFactory() {
        this("delault");
    }


    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new MyAppThread(r);
    }

    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(100), new MyThreadFactory("mythrad"));
        Stream.of(1,2,3,4,5,6,7,8,9).forEach((a)->{
            poolExecutor.execute(()->{
                System.out.println(a);
            });
        });
    }
}
class MyAppThread extends Thread{
    public static final String DEFAULT_NAME="MyAppThread";
    private static volatile boolean debugLifeCycle = true;
    private static final AtomicInteger created = new AtomicInteger();
    private static final AtomicInteger alive = new AtomicInteger();

    public MyAppThread(Runnable runnable){
        this(runnable,DEFAULT_NAME);
    }

    public MyAppThread(Runnable runnable,String name){
        super(runnable,name +"-"+created.incrementAndGet());
        setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Uncatch in" + t.getName() +e);
            }
        });
    }

    @Override
    public void run() {
        boolean debug = debugLifeCycle;
        if (debug){
            System.out.println("debug-"+"create" +getName());
        }
        try {
            alive.incrementAndGet();
            super.run();
        } finally {
            alive.decrementAndGet();
            if (debug){
                System.out.println("debug-"+"exiting" +getName());
            }
        }
    }

    public static String getDefaultName() {
        return DEFAULT_NAME;
    }

    public static boolean isDebugLifeCycle() {
        return debugLifeCycle;
    }

    public static void setDebugLifeCycle(boolean debugLifeCycle) {
        MyAppThread.debugLifeCycle = debugLifeCycle;
    }

    public static Integer getCreated() {
        return created.get();
    }

    public static Integer getAlive() {
        return alive.get();
    }
}