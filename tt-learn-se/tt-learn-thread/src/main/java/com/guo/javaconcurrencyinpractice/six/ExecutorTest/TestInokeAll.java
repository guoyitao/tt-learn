package com.guo.javaconcurrencyinpractice.six.ExecutorTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class TestInokeAll {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Callable<String>> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            list.add(()->{
                Thread.sleep(1000);
                return "future:"+ finalI;
            });
        }
        try {
            System.out.println("start invokeAll");
            //批量提交任务并阻塞到任务全部完成
            List<Future<String>> list1 = executorService.invokeAll(list);
            System.out.println("stop invokeAll ");
            //并行遍历
            list1.parallelStream().forEach((a)->{
                try {
                    System.out.println(a.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
