package com.guo.javaconcurrencyinpractice.sevencancel;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Description: 因为生产者速度快于消费者，而无法结束
 * @Author: guo
 * @CreateDate: 2019/4/8
 * @UpdateUser:
 */
public class BrokenPrimeProducer extends Thread {
    private final BlockingQueue<BigInteger> queue;
    private volatile boolean cancelled = false;

    public BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        BigInteger one = BigInteger.ONE;
        while (!cancelled){
            try {
                queue.put(one = one.nextProbablePrime());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setCancelled(){
        cancelled = true;
    }

    public static void main(String[] args) {
        ArrayBlockingQueue<BigInteger> queue = new ArrayBlockingQueue<BigInteger>(100) {
        };
        BrokenPrimeProducer brokenPrimeProducer = new BrokenPrimeProducer(queue);
        new Thread(brokenPrimeProducer).start();

        try {
            while (true){
                System.out.println(queue.take());
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            brokenPrimeProducer.setCancelled();
        }


    }
}
