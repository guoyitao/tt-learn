package com.guo.threadreadbattcn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.stream.IntStream;

/**
 * 1.创建线程池，销毁线程池，添加新任务
 *
 * 2.没有任务进入等待，有任务则处理掉
 *
 * 3.动态伸缩，扩容
 *
 * 4.拒绝策略
 * @Description:
 * @Author: guo
 * @CreateDate: 2019/4/3
 * @UpdateUser:
 */
public class SimpleThreadPoolExecutor extends Thread implements Executor {
    // 线程池大小
    private int threadPoolSize;
    // 最大接收任务
    private int queueSize;
    // 拒绝策略
    private DiscardPolicy discardPolicy;
    // 是否被销毁
    private volatile boolean destroy = false;

    private final static int DEFAULT_MIN_THREAD_SIZE = 2;// 默认最小线程数
    private final static int DEFAULT_MAX_THREAD_SIZE = 10;// 最大线程
    private final static int DEFAULT_ACTIVE_THREAD_SIZE = 5;// 活跃线程
    private final static int DEFAULT_WORKER_QUEUE_SIZE = 100;// 最多执行多少任务
    private final static String THREAD_NAME_PREFIX = "MY-THREAD-NAME-";//线程名前缀
    private final static String THREAD_POOL_NAME = "SIMPLE-POOL";//线程组的名称
    private final static ThreadGroup THREAD_GROUP = new ThreadGroup(THREAD_POOL_NAME);//线程组
    private final static List<WorkerTask> WORKER_TASKS = new ArrayList<>();// 线程容器
    // 任务队列容器,也可以用Queue<Runnable> 遵循 FIFO 规则
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();
    // 拒绝策略
    private final static DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new DiscardException("[拒绝执行] - [任务队列溢出...]");
    };

    private int minSize;//最小线程
    private int maxSize;//最大线程

    private int activeSize;//活跃线程

    SimpleThreadPoolExecutor() {
        this(DEFAULT_MIN_THREAD_SIZE, DEFAULT_ACTIVE_THREAD_SIZE, DEFAULT_MAX_THREAD_SIZE, DEFAULT_WORKER_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
    }

    SimpleThreadPoolExecutor(int minSize, int activeSize, int maxSize, int queueSize, DiscardPolicy discardPolicy) {
        this.minSize = minSize;
        this.activeSize = activeSize;
        this.maxSize = maxSize;
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;
        initPool();
    }

    private void initPool() {
        for (int i = 0;i<this.minSize;i++){
            this.createTaskWork();
        }
        this.threadPoolSize = minSize;
        this.start(); //启动自己
    }

    private void createTaskWork() {
        WorkerTask task = new WorkerTask();
        //刚创建出来的线程应该是未使用的
        task.taskState = TaskState.FREE;
        WORKER_TASKS.add(task);
        task.start();
    }

    @Override
    public void execute(Runnable command) {
        if (destroy){
            throw new IllegalStateException("线程池已经销毁。。。");
        }
        synchronized (TASK_QUEUE){
            if (TASK_QUEUE.size() > queueSize){
                discardPolicy.discard();
            }
            TASK_QUEUE.add(command);
            TASK_QUEUE.notifyAll();
        }
    }
    //main线程调用
    public void shutdown() throws InterruptedException {
        //活动线程
        int activeCount = THREAD_GROUP.activeCount();
        while (!TASK_QUEUE.isEmpty()&& activeCount>0){
            // 如果还有任务,那就休息一会
            Thread.sleep(100);
        }

        int intVal  = WORKER_TASKS.size();
        while (intVal >0){
            for (WorkerTask workerTask : WORKER_TASKS) {
                //如果没事做
                if (workerTask.taskState ==TaskState.BLOCKED){
                    workerTask.close();
                    intVal --;
                }else{
                    Thread.sleep(50);
                }
            }
        }
        this.destroy = true;
        //资源回收
        TASK_QUEUE.clear();
        WORKER_TASKS.clear();
        this.interrupt();
        System.out.println("线程关闭");

    }
    //在main线程启动
    @Override
    public void run() {
        while (!destroy){

            try {
                Thread.sleep(2000);
                if (TASK_QUEUE.size() > activeSize && threadPoolSize < activeSize){ // 第一次扩容到 activeSize 大小
                    for (int i = threadPoolSize; i < activeSize; i++) {
                        createTaskWork();
                    }
                    this.threadPoolSize = activeSize;
                    System.out.println("[初次扩充] - 【"+toString()+"】");
                }else if (TASK_QUEUE.size() > maxSize && threadPoolSize <maxSize){
                    System.out.println();
                    for (int i = threadPoolSize; i < maxSize; i++) {
                        createTaskWork();
                    }
                    this.threadPoolSize = maxSize;
                    System.out.println("[再次扩充] - 【"+toString()+"】");

                }else if (TASK_QUEUE.size() < threadPoolSize ){
                    //有重复回收bug  //TODO 现在的activeSize不是当前 正在干活数 ，把if else 换成当前正在干活数 < threadPoolSize ，就把没在干活的线程干掉
                    //资源回收
                    //防止线程在submit的时候，其他线程获取到锁干坏事
                    synchronized (WORKER_TASKS){
                        Iterator<WorkerTask> iterator = WORKER_TASKS.iterator();// List不允许在for中删除集合元素,所以这里需要使用迭代器
                        while (iterator.hasNext()) {
                            WorkerTask task = iterator.next();
                            //不能回收正在运行的线程,只回收空闲线程,回收之后的线程数为  最小线程数              //TODO 这里可以把线程池的线程数缩减为  正在干活数
                            if (task.taskState == TaskState.BLOCKED && threadPoolSize > minSize) {
                                //中断，把线程从线程容器删除，让他不能再执行任务，等到下次任务多起来的时候进行扩容就可以增加活动线程了
                                task.close();
                                iterator.remove();
                                System.out.println("[资源回收] - [" + toString() + "]");
                                threadPoolSize--;
                            }
                        }

                    }
                }

            } catch (InterruptedException e) {

                System.out.println("资源释放");
            }
        }
    }

    /**
     * 线程状态,直接复用的Thread 中的
     */
    private enum TaskState {
        FREE, RUNNABLE, BLOCKED, TERMINATED;
    }

    /**
     * 决绝策略接口
     */
    interface DiscardPolicy {
        void discard() throws DiscardException;
    }

    static class DiscardException extends RuntimeException {
        private static final long serialVersionUID = 8827362380544575914L;

        DiscardException(String message) {
            super(message);
        }
    }

    public static class WorkerTask extends Thread {
        //线程状态
        private TaskState taskState;
        //线程编号
        private static int threadNumber;
        //生成线程名称
        private static synchronized String nextThreadName(){
            return THREAD_NAME_PREFIX + (++threadNumber);
        }

        WorkerTask() {
            super(THREAD_GROUP, nextThreadName());
        }

        @Override
        public void run() {
            Runnable target = null;
            OUTER:
            while (this.taskState != TaskState.TERMINATED){
                synchronized (TASK_QUEUE) {
                    //如果没有任务
                    while (this.taskState == TaskState.FREE && TASK_QUEUE.isEmpty()) {
                        try {
                            this.taskState = TaskState.BLOCKED;
                            //没有任务就wait住,让出CPU执行权
                            TASK_QUEUE.wait();
                            //如果被打断说明当前线程执行了 shutdown() 方法  线程状态为 TERMINATED 直接跳到 while 便于退出
                        } catch (InterruptedException e) {
                            break OUTER;
                        }
                    }
                    //取出任务
                    target = TASK_QUEUE.removeFirst();//遵循FIFO策略
                }
                if (target != null) {
                    this.taskState = TaskState.RUNNABLE;
                    target.run();//开始任务了
                    this.taskState = TaskState.FREE;
                }
            }
        }

        void close() {//优雅关闭线程
            this.taskState = TaskState.TERMINATED;
            this.interrupt();
        }
    }


    @Override
    public String toString() {
        return "SimpleThreadPoolExecutor{" + "threadPoolSize=" + threadPoolSize + ", queueSize=" + queueSize + ", discardPolicy=" + discardPolicy + ", destroy=" + destroy + ", minSize=" + minSize + ", maxSize=" + maxSize + ", activeSize=" + activeSize + '}';
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleThreadPoolExecutor executor = new SimpleThreadPoolExecutor();
        IntStream.range(0, 90).forEach(i ->
                executor.execute(() -> {
                    System.out.printf("[线程] - [%s] 开始工作...\n", Thread.currentThread().getName());
                    try {
                        Thread.sleep(2_000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.printf("[线程] - [%s] 工作完毕...\n", Thread.currentThread().getName());
                })
        );
        executor.shutdown();
    }
}
