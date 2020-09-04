package com.guo.mashibing;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

//延迟队列测试
//场景：订单未付款状态定时删除
public class TestDelayQueue {

    static BlockingQueue<MyTask> tasks = new DelayQueue();

    static Random random = new Random();


    static class MyTask implements Delayed {
        long runstartTime;

        public MyTask(long runstartTime) {
            this.runstartTime = runstartTime;
        }

        //返回需要延迟多久
        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runstartTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public String toString() {
            return "MyTask{" + "runstartTime=" + runstartTime + '}';
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
                return -1;
            } else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
                return 1;
            } else {
                return 0;
            }


        }
    }

    public static void main(String[] args) throws InterruptedException {
        long l = System.currentTimeMillis();
        tasks.put(new MyTask(l + 1000));
        tasks.put(new MyTask(l + 3000));
        tasks.put(new MyTask(l + 1500));
        tasks.put(new MyTask(l + 2500));
        tasks.put(new MyTask(l + 2000));

        for (int i = 0; i < 5; i++) {
            MyTask take = tasks.take();
            System.out.println(take);

        }
    }
}
