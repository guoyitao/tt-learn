package com.guo.javaconcurrencyinpractice.sevencancel;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 *
 * @Description: 存在问题 当生产者任务里面调用了会阻塞的方法，如果消费者消费速度比生产者慢就永远不会结束  {@link BrokenPrimeProducer}
 * @Author: guo
 * @CreateDate: 2019/4/8
 * @UpdateUser:
 */
public class PrimeGenerator implements Runnable {
    private final List<BigInteger> prims = new ArrayList<>();
    private volatile boolean  cancelled = false;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled){
            p = p.nextProbablePrime();
            synchronized (this){
                prims.add(p);
            }
        }
    }

    public void cancel() {
        cancelled = false;
    }

    public synchronized List<BigInteger> getPrims() {
        return new ArrayList<>(prims);
    }

    public static void main(String[] args) {
        PrimeGenerator primeGenerator = new PrimeGenerator();
        new Thread(primeGenerator).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            primeGenerator.cancel();
            List<BigInteger> prims = primeGenerator.getPrims();
            System.out.println(prims);

        }
    }
}
