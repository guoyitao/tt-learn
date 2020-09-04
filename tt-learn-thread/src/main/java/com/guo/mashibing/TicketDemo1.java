package com.guo.mashibing;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

public class TicketDemo1 {

    private static List<Integer> list = new LinkedList<>();

    private static Queue<Integer> queue;

    interface Callback {
        void buy();
    }

    static {
        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }
        queue = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < 1000000; i++) {
            queue.add(i);
        }
    }


    public static void startThread(Callback callback, int Tnum) {
        for (int i = 0; i < Tnum; i++) {
            new Thread(() -> {
                callback.buy();
            }, "购买者线程" + i).start();
        }
    }

    public static void main(String[] args) {

        long l = System.currentTimeMillis();
        buyConcurrentLinkedQueue();
//        buySync();
        long l1 = System.currentTimeMillis();

        System.out.println("一共花了：   " + (l1 - l));
    }


    public static void buyConcurrentLinkedQueue() {
        int i = 40;
        CountDownLatch countDownLatch = new CountDownLatch(i);

        startThread(() -> {
            while (true) {
                Integer poll = queue.poll();
                if (poll == null) {
//                    System.out.printf("count down" + queue.size());
                    countDownLatch.countDown();
                    break;
                }
//                System.out.printf(Thread.currentThread().getName() + "  :  " + poll);
            }
        }, i);

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private static void buySync() {
        int i = 40;
        CountDownLatch countDownLatch = new CountDownLatch(i);
        startThread(() -> {
            while (true) {
                synchronized (list) {
                    if (list.size() <= 0) {
//                        System.out.printf("count down" + list.size());
                        countDownLatch.countDown();
                        break;
                    }
                    Integer remove = list.remove(0);
//                    System.out.printf(Thread.currentThread().getName() + "  :  " + remove);
                }
            }
        }, i);

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
