package com.guo.javaconcurrencyinpractice.fiveblockqueuefutruesemaphore;

import lombok.Data;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @Description: 多线程累加
 * @Author: guo
 * @CreateDate: 2019/4/7
 * @UpdateUser:
 */
//还没做完，拆分任务有问题
public class CountOneToXX {
    public static CountDownLatch startGate = new CountDownLatch(1);
    public static CountDownLatch endGate;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long count = count(0, 10, 2);
        System.out.println(count);

        //非多线程版本
        int result = 0;
        for (int i = 0; i < 10; i++) {
            result+=i;
        }
        System.out.println(result);
    }

    public static long count(long from,long to,int taskNum) throws InterruptedException, ExecutionException {
        endGate = new CountDownLatch(taskNum);
        ArrayList<Future<Long>> longs = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(taskNum);

        long shouldDo = to - from;
        int incremental = Math.round(shouldDo / taskNum);

        boolean isFirst = true;
        for (int i = 0; i < taskNum; i++) {
            long toData = from+incremental;
            Future<Long> submit = executorService.submit(new MyTask(from ,toData));
            longs.add(submit);
            from +=incremental;
        }
        //开始任务
        startGate.countDown();

        endGate.await();
        long result = 0;
        for (Future<Long> aLong : longs) {
           result += aLong.get();
        }
        return result;
    }
    @Data
    static class MyTask implements Callable<Long>{
        private Long start;
        private Long end;

        public MyTask(Long start, Long end) {
            this.start = start;
            this.end = end;
        }


        @Override
        public Long call() throws Exception {
            startGate.await();
            long result = start;
            for (long i = start; i < end; i++) {
                result += i;
            }
            endGate.countDown();
            return result;
        }
    }
}
