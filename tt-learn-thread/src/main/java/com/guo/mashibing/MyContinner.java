package com.guo.mashibing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

/**
 * @Description: 阿里面试
 * 实现一个容器提供两个方法，add，size
 * 两个线程线程1添加10个元素到容器，线程2实现监控元素个数，当数到5个时，线程2给出提示并结束
 * @Author: guo
 * @CreateDate: 2019/6/10
 * @UpdateUser:
 */
public class MyContinner {

    List<Integer> list = new ArrayList<>();


    public synchronized int size() {
        return list.size();
    }

    public synchronized void add(int i) {
        list.add(i);
    }

    @Override
    public String toString() {
        return "MyContinner{" + "list=" + list + '}';
    }

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        MyContinner continner = new MyContinner();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                continner.add(i);
                countDownLatch.countDown();
            }
        });
        thread.start();

        new Thread(()->{
            try {
                countDownLatch.await();
                System.out.printf("5个了" + continner.toString());
            } catch (InterruptedException e) {
                thread.interrupt();
            }
        }).start();


    }
}
