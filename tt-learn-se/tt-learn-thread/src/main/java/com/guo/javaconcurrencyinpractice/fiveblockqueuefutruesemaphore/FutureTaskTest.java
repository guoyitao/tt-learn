package com.guo.javaconcurrencyinpractice.fiveblockqueuefutruesemaphore;

import com.guo.javaconcurrencyinpractice.testexception.MyRuntimeException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description: 异步操作
 * @Author: guo
 * @CreateDate: 2019/4/7
 * @UpdateUser:
 */
public class FutureTaskTest {

    private FutureTask<String> futureTask = new FutureTask<>(()->{
        System.out.println("runing");
        Thread.sleep(2000);
        System.out.println("stop");

        throw new MyRuntimeException("hello error");
//        return "hello";
    });

    private Thread thread = new Thread(futureTask);

    public static void main(String[] args) {
        FutureTaskTest futureTaskTest = new FutureTaskTest();
        futureTaskTest.thread.start();
        try {
            //直到这个线程运行完毕才返回
            System.out.println(futureTaskTest.futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            //callable里面抛出任何异常都会被封装成ExecutionException，需要额外处理
            Throwable cause = e.getCause();
            if (cause instanceof MyRuntimeException){
                throw  (MyRuntimeException) cause;
            }else {
                throw launderThrowable(cause);
            }
        }
    }

    public static RuntimeException launderThrowable(Throwable t){
        if (t instanceof RuntimeException){
            return (RuntimeException) t;
        }else if (t instanceof Error){
            throw (Error)t;
        }else {
            throw new IllegalStateException(t);
        }
    }
}
