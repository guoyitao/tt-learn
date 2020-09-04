package com.guo.mashibing;

import java.util.concurrent.*;

public class TestFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(()->{
            TimeUnit.SECONDS.sleep(1);
            return 1000;
        });  //相当于 传入callable

        new Thread(task).start();

        System.out.println(task.get()); //阻塞等待任务执行结束

        ExecutorService service = Executors.newFixedThreadPool(5);


        Future<Integer> submit = service.submit(()->{
            TimeUnit.MILLISECONDS.sleep(100);
            return 1;
        });

        System.out.println("提交的任务执行完了吗"+ submit.isDone());
        System.out.println(submit.get());
        System.out.println("提交的任务执行完了吗"+ submit.isDone());


    }
}
