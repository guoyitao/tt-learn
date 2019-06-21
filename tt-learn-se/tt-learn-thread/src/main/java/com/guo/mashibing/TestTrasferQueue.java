package com.guo.mashibing;

import java.util.concurrent.LinkedTransferQueue;


//transfer了之后直接给消费者，中间没有队列，如果没有消费者就会一直阻塞
public class TestTrasferQueue {

    public static void main(String[] args) {
        LinkedTransferQueue<String> queue = new LinkedTransferQueue<>();

        new Thread(()->{
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            queue.transfer("111");


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        new Thread(()->{
//            try {
//                System.out.println(queue.take());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
    }
}
