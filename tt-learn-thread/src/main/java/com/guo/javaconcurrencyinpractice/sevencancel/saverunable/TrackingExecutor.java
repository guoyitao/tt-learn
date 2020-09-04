package com.guo.javaconcurrencyinpractice.sevencancel.saverunable;

import java.util.*;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//不太懂
public class TrackingExecutor extends AbstractExecutorService {
    private final ExecutorService exc;
    private final Set<Runnable> tasksCancellAtShutDown = Collections.synchronizedSet(new HashSet<>());


    public TrackingExecutor(ExecutorService exc) {
        this.exc = exc;
    }
    //得到被取消的任务
    public ArrayList<Runnable> getTasksCancellAtShutDown() {

        if (!exc.isTerminated()){
            throw new IllegalStateException("getTasksCancellAtShutDown");
        }
        return new ArrayList<Runnable>(tasksCancellAtShutDown);
    }

    @Override
    public void shutdown() {
        exc.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {
        return exc.shutdownNow();
    }

    @Override
    public boolean isShutdown() {
        return exc.isShutdown();
    }

    @Override
    public boolean isTerminated() {
        return exc.isTerminated();
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit)  {
        try {
            return exc.awaitTermination(timeout, unit);
        } catch (InterruptedException e) {
            return exc.isTerminated();
        }
    }

    @Override
    public void execute(Runnable command) {
        exc.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    command.run();
                } finally {
                    if (isShutdown() && Thread.currentThread().isInterrupted()){
                        tasksCancellAtShutDown.add(command);
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        TrackingExecutor trackingExecutor = new TrackingExecutor(Executors.newFixedThreadPool(10));

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            trackingExecutor.execute(()->{
                try {
                    Thread.sleep(1000);
                    System.out.println(finalI);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            });
        }

        trackingExecutor.shutdownNow();
        ArrayList<Runnable> tasksCancellAtShutDown = trackingExecutor.getTasksCancellAtShutDown();
        System.out.println(tasksCancellAtShutDown);

    }
}
