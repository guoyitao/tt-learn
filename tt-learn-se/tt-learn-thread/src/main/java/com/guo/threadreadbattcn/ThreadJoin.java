package com.guo.threadreadbattcn;

import com.guo.DataClass;

/**
 *
 * join() 的作用：让主线程等待子线程结束之后才能继续运行，               下以采集为案例的代码，统计采集所消耗的时长
 *
 *
 * 在start方法下添加join操作，运行main方法，发现不管那个线程先执行，结果都是5000毫秒以上，
 * 因为主线程main接收到了thread1,thread2两个线程的join指令，这个时候主线程则会处于阻塞状态，直到子线程执行完毕后才会继续执行下去
 *
 * 在第一个子线程线程join的时候，第二个子线程还在是出于活动状态
 * @Description:
 * @Author: guo
 * @CreateDate: 2019/4/2
 * @UpdateUser: 1
 */
public class ThreadJoin extends DataClass {

    public static void main(String[] args) {
        ThreadJoin threadJoin = new ThreadJoin();
        threadJoin.startTime();

        Thread thread1 = new Thread(new CollecterTask("One",1000L));
        Thread thread2 = new Thread(new CollecterTask("Tow",5000L));
        thread1.start();
        thread2.start();
        try {
            System.out.println(".1.");
            thread1.join();
            System.out.println(".2.");
            thread2.join();
            System.out.println(".3.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        threadJoin.endTime();

    }

    static class CollecterTask implements Runnable{

        private String name;
        private Long collectTime;

        public CollecterTask(String name, Long collectTime) {
            this.name = name;
            this.collectTime = collectTime;
        }


        @Override
        public void run() {
            System.out.println(name+collectTime + "正在采集。。。。。。。。。。");
            try {
                Thread.sleep(collectTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

