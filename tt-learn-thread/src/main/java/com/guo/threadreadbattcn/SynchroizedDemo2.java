package com.guo.threadreadbattcn;

/**
 * @Description: 作用在普通方法上时，锁是当前 实例对象
 * @Author: guo
 * @CreateDate: 2019/4/2
 * @UpdateUser:
 */
public class SynchroizedDemo2 implements Runnable {

    @Override
    public synchronized void run() {
        System.out.println("查询数据：" + Thread.currentThread().getName());
        System.out.println("开始转账：" + Thread.currentThread().getName());
        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("转账完毕");
    }

    public static void main(String[] args) {
        SynchroizedDemo2 run1 = new SynchroizedDemo2();
        SynchroizedDemo2 run2 = new SynchroizedDemo2();

        new Thread(run1,"上海").start();
        new Thread(run2,"上海").start();
    }
}
