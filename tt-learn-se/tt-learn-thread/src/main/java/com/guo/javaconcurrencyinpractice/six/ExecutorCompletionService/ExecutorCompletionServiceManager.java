package com.guo.javaconcurrencyinpractice.six.ExecutorCompletionService;

import com.guo.threadreadbattcn.ThreadJoin;

import java.util.concurrent.*;

/**ExecutorCompletionService 完成的任务会自动的被添加到ExecutorCompletionService的private final BlockingQueue<Future<V>> completionQueue;
 * @Description:
 * @Author: guo
 * @CreateDate: 2019/4/8
 * @UpdateUser:
 */
public class ExecutorCompletionServiceManager {
    public static void main(String[] args) {

        ExecutorCompletionService<String> service = new ExecutorCompletionService<String>(
                Executors.newFixedThreadPool(30));

        // 生产者
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    int index = i;
                    service.submit(new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            Thread.sleep(100*index);
                            return "task done" + index;
                        }
                    });
                }
            }
        }.start();


        // 消费者
        new Thread() {
            @Override
            public void run() {
                try {
                    while (true){
                        //从completionQueue取出已经完成的任务
                        // do some thing........
                        Future<String> take = service.take();
                        System.out.println("comple:" +take.get());
                    }


                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
