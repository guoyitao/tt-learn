package com.guo.javaconcurrencyinpractice.fiveblockqueuefutruesemaphore;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * -	    Throws Exception	Special Value	Blocks	Times Out
 * Insert	add(o)	            offer(o)	    put(o)	offer(o, timeout, timeunit)
 * Remove	remove(o)	        poll()	        take()	poll(timeout, timeunit)
 * Examine	element()	        peek()
 * 1. ThrowsException：如果操作不能马上进行，则抛出异常
 * 2. SpecialValue：如果操作不能马上进行，将会返回一个特殊的值，一般是true或者false
 * 3. Blocks:如果操作不能马上进行，操作会被阻塞
 * 4. TimesOut:如果操作不能马上进行，操作会被阻塞指定的时间，如果指定时间没执行，则返回一个特殊值，一般是true或者false
 *
 * @Description:
 * @Author: guo
 * @CreateDate: 2019/4/6
 * @UpdateUser:
 */
public class ArrayBlockingQueueTest<T> {
    public  BlockingQueue<T> blockingQueue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {
        ArrayBlockingQueueTest<String> arrayBlockingQueueTest = new ArrayBlockingQueueTest<>();

        new Thread(()->{
            for (int j = 0; j < 10000; j++) {
                try {
                    arrayBlockingQueueTest.blockingQueue.put(j+"-put  ");
                    System.out.println("生产-：  "+j+"-put  ");
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();



        sleep();
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                while (true){
                    try {
                        String take = arrayBlockingQueueTest.blockingQueue.take();
                        System.out.println("消费-:   "+ take);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }

    public static void sleep() {
        try {
            System.out.println("--------------startSleep-----------");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("--------------endSleep--------------");
        }
    }
}
