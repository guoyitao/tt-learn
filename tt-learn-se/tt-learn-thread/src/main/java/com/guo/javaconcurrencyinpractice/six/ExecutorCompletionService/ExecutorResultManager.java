package com.guo.javaconcurrencyinpractice.six.ExecutorCompletionService;

import java.util.concurrent.*;

public class ExecutorResultManager {

    public static void main(String[] args) {
        // 队列
        BlockingQueue<Future<String>> futures = new LinkedBlockingQueue<>();

        // 生产者
        new Thread() {
            @Override
            public void run() {
                ExecutorService pool = Executors.newCachedThreadPool();

                for (int i=0; i< 10; i++) {
                    int index = i;
                    Future<String> submit = pool.submit(new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            Thread.sleep(1000*index);
                            return "task done" + index;
                        }
                    });
                    try {
                        futures.put(submit);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();



        // 消费者
        new Thread() {
            @Override
            public void run() {
                while(true) {
                    for (Future<String> future : futures) {
                        if(future.isDone()) {
                            try {
                                System.out.println(future.get());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                            // 处理业务
                            // .............


                        };
                    }
                }
            }
        }.start();
    }

}
