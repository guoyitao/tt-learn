package com.guo.javaconcurrencyinpractice.sevencancel;

import com.guo.Bad;
import com.guo.Good;
import com.guo.javaconcurrencyinpractice.fiveblockqueuefutruesemaphore.FutureTaskTest;
import org.junit.Test;

import java.util.concurrent.*;

/**
 * @Description:
 * @Author: guo
 * @CreateDate: 2019/4/8
 * @UpdateUser:
 */
public class CancelThreadOutTest {

    private static final ScheduledExecutorService cancelExec = Executors.newScheduledThreadPool(10);
    private static final ExecutorService runExec = Executors.newFixedThreadPool(10);

    @Bad
    //错误的中断,timedRun可以由任意一个线程调配，所以它无法知道这个调用线程的中断策略
    public static void timedRun(Runnable r, long timeout, TimeUnit unit){
        Thread currentThread = Thread.currentThread();
        cancelExec.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("cancelExec will interrupt");
                currentThread.interrupt();
            }
        }, timeout, unit);
        r.run();
    }

    @Bad
    //把异常传递到调用timeRun2(),在调用者线程处理中断逻辑
    //问题，无法知道控制流程是因为线程的正常退出还是超时,反正问题很多
    public static void timeRun2(final Work r, long timeout,TimeUnit unit) throws InterruptedException {

        class RethrowableTask implements Runnable{
            private volatile Throwable throwable;

            @Override
            public void run()  {
                try {
                    r.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                   this.throwable  =e;
                }

            }
            void rethrow(){
                if (throwable != null){
                    throw FutureTaskTest.launderThrowable(throwable);
                }
            }
        }

        RethrowableTask task = new RethrowableTask();
        Thread taskThread = new Thread(task);
        taskThread.start();

        cancelExec.schedule(()->{
            //中断了之后就会给throwable赋值
            taskThread.interrupt();
        },timeout,unit);

        //抢占掉用线程超时后就过
        taskThread.join(unit.toMillis(timeout+1));
        //传递异常
        task.rethrow();
    }


    @Good
    private static void timedRun3(Runnable runnable,long timeout,TimeUnit unit){

        Future<?> task = runExec.submit(runnable);


        try {
            task.get(timeout, unit);
        } catch (InterruptedException e) {
            e.printStackTrace();
            //中断处理
        } catch (ExecutionException e) {
            //处理任务中的异常
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
            //超时处理
        } finally {
            //任务结束，如果任务运行完成则没有影响，如果真正运行就会被中断
            task.cancel(true);
        }
    }

    interface Work{
       void run() throws InterruptedException;
    }

    public static void main(String[] args) {
        try {
            timeRun2(new Work(){

                @Override
                public void run() throws InterruptedException {
                    Thread.sleep(11000);
                }
            },2,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("中断异常从里面出来了");
            e.printStackTrace();
        }
    }

    @Test
    public void test1(){

        timedRun3(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
        },2,TimeUnit.SECONDS);

    }


}
