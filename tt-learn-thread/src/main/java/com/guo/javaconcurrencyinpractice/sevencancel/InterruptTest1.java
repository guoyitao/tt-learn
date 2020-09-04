package com.guo.javaconcurrencyinpractice.sevencancel;

import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 线程中断之后再起
 * @Description:
 * @Author: guo
 * @CreateDate: 2019/4/8
 * @UpdateUser:
 */
public class InterruptTest1 extends Thread {
    private final BlockingQueue<BigInteger> queue;

    public InterruptTest1(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!Thread.currentThread().isInterrupted()){
            try {
                queue.put(p = p.nextProbablePrime());
                System.out.println(p);
            } catch (InterruptedException e) {
                System.out.println("中断了" +e.getMessage());

            }
        }
    }

    public void cancel(){
        interrupt();
    }

    public static void main(String[] args) {
        ArrayBlockingQueue<BigInteger> queue = new ArrayBlockingQueue<>(100);
        InterruptTest1 test1 = new InterruptTest1(queue);
        test1.start();

        new Thread(()->{
            while (true){
                BigInteger nextBigInter = test1.getNextBigInter(queue);
            }
        }).start();

        test1.cancel();
    }

    public BigInteger getNextBigInter(BlockingQueue<BigInteger> queue){
        boolean interrupted = false;
        try {
            while (true){
                try {
                    return queue.take();
                } catch (InterruptedException e) {
                    interrupted = true;
                }
            }
        } finally {
            if (interrupted){
                Thread.currentThread().interrupt();
            }
        }
    }
}