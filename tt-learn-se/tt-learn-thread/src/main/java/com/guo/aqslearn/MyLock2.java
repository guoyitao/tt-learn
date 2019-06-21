package com.guo.aqslearn;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

//无意义只是演示java.util.concurrent.locks.Lock; 怎么用
public class MyLock2 implements Lock {

    private int value= 0;
    @Override
    public void lock() {
        synchronized (this){
            while (value == 1){ //已经有线程占用
                try {
                    this.wait(); //阻塞 CAS 一直在自旋
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            value = 1; //获得锁
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        synchronized (this){
            //锁开了
            value = 0;
            //唤醒当前对象等待池里面所有等待的线程
            this.notifyAll();
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
