package com.guo.mashibing;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;


/**
 * @Description:  同步队列
 *
 * 一个容量为0的队列
 * put之后必须有消费者消费了才结束阻塞
 *
 *
 * @Author: guo
 * @CreateDate: 2019/6/12
 * @UpdateUser:
 */
public class TestSyncQueue {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new SynchronousQueue<>();

//        new Thread(()->{
//            try {
//                System.out.println(queue.take());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }).start();

        queue.put("131321321");

        queue.size();
    }
}
