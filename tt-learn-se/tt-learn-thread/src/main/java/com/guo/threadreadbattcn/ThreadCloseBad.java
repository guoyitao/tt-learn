package com.guo.threadreadbattcn;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Description: 线程的优雅关闭   两步终止模式
 * 问题：{
 *     存在非安全线程死亡，任务没完成直接断开
 * }
 * @Author: guo
 * @CreateDate: 2019/4/2
 * @UpdateUser: 2
 */
public class ThreadCloseBad extends Thread{

    private volatile  AtomicBoolean isShutdown = new AtomicBoolean(true);

    public void shutdown() {
        System.out.println("接收到关闭通知......");
        this.isShutdown.set(false);
        //线程中断
        interrupt();
    }

    @Override
    public void run() {
        while (this.isShutdown.get()){
            System.out.println("working....." + System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                //线程中断
                System.out.println(e.getMessage() + " :work interrupt");
            }
        }
        System.out.println("distory");
    }

    public static void main(String[] args) {
        ThreadCloseBad threadCloseBad = new ThreadCloseBad();
        threadCloseBad.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadCloseBad.shutdown();

    }
}
