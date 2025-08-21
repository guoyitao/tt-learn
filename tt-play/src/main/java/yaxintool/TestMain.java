 package yaxintool;

 import java.util.concurrent.ArrayBlockingQueue;
 import java.util.concurrent.BlockingQueue;
 import java.util.concurrent.ThreadPoolExecutor;
 import java.util.concurrent.TimeUnit;

 /**
 * 1.使用多线程实现生产者消费者模型，生产者生产数据，消费者消费数据
 */
public class TestMain {
    // 定义阻塞队列
    static BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {
        //创建线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                8,
                16,
                100,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1000),
                new ThreadPoolExecutor.DiscardOldestPolicy()
        );

        // 提交生产者任务
        threadPoolExecutor.submit(new Producer("Producer-1"));
        threadPoolExecutor.submit(new Producer("Producer-2"));

        // 提交消费者任务
        threadPoolExecutor.submit(new Consumer("Consumer-1"));
        threadPoolExecutor.submit(new Consumer("Consumer-2"));

        //结束线程池
        threadPoolExecutor.shutdown();
    }

    // 生产者任务
    static class Producer implements Runnable {
        private String name;

        public Producer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 100; i++) {
                    String data = "消息-" + i;
                    queue.put(data);
                    System.out.println(name + " 生产: " + data);
                    Thread.sleep(1000L);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // 消费者任务
    static class Consumer implements Runnable {
        private String name;

        public Consumer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 100; i++) {
                    String data = queue.take();
                    System.out.println(name + " 消费: " + data);
                    Thread.sleep(1000L);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
