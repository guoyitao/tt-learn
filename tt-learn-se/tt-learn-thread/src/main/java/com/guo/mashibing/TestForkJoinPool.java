package com.guo.mashibing;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

public class TestForkJoinPool {



    static int[] nums = new int[1000000];
    static final int MAX_NUM  = 50000; //最小任务 单位
    static Random random = new Random();

    static {
        for (int i = 0; i <nums.length ; i++) {
            nums[i] = random.nextInt(30);
        }

        System.out.println(Arrays.stream(nums).sum());
    }

    //无返回值任务
    static class AddTask2 extends RecursiveAction{
        int start,end;

        public AddTask2(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            //计算
            if (end-start <= MAX_NUM){
                long sum0 = 0;
                for (int i = start; i < end; i++) {
                    sum0 += nums[i];
                    System.out.println("from " + start + " to " + end + " -sum: " + sum0);
                }
            }else {
                //分
                //取中间值
                int middle = start + (end - start) / 2;
                AddTask task1 = new AddTask(start, middle);
                AddTask task2 = new AddTask(middle, end);

                task1.fork();
                task2.fork();
            }
        }
    }

    static class AddTask extends RecursiveTask<Long> {
        int start,end;

        public AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            //计算
            if (end - start <= MAX_NUM){
                long sum0 = 0;
                for (int i = start; i < end; i++) {
                    sum0 += nums[i];

                }
                System.out.println("from " + start + " to " + end + " -sum: " + sum0);
                return sum0;
            }else {
                //分
                //取中间值
                int middle = start + (end - start) / 2;
                AddTask task1 = new AddTask(start, middle);
                AddTask task2 = new AddTask(middle, end);

                //分！
                task1.fork();
                task2.fork();

                //得出任务 返回值 ！
                Long sum1 = task1.join();
                Long sum2 = task2.join();
                return sum1 + sum2;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ExecutorService stealingPool = Executors.newWorkStealingPool();


        ForkJoinPool forkJoinPool = new ForkJoinPool();

//        AddTask addTask = new AddTask(0,nums.length);
        AddTask2 addTask = new AddTask2(0,nums.length);

        forkJoinPool.execute(addTask);

//        Long join = addTask.join();
//        System.out.println(join);

        System.in.read();
    }

    //有返回值任务
}
