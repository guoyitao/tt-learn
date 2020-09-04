package com.guo.javaconcurrencyinpractice.fiveblockqueuefutruesemaphore;

import lombok.Data;

import java.util.concurrent.*;

@Data
class MyData implements Delayed {
    private long delay;//延迟时间
    private long expired;//啥时候过期
    private String name;

    public MyData(long delay, String name) {
        this.delay = delay;
        this.name = name;
        expired = ( delay + System. currentTimeMillis());
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return expired - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        MyData myData = (MyData) o;
        return myData.expired > expired ? 1 : -1;
    }
}

public class DelayQueueTest {
    public static DelayQueue<MyData> delayQueue = new DelayQueue<>();

    public static void main(String[] args) {
        MyData myData = new MyData(4000,"xiaoming");
        delayQueue.put(myData);

        try {
            System.out.println("start take");
            MyData take = delayQueue.take();
            System.out.println("getdata "+ take);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
