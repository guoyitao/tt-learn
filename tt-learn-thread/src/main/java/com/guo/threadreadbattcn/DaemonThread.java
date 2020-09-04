package com.guo.threadreadbattcn;

/**
 * @Description: 守护进程
 * @Author: guo
 * @CreateDate: 2019/4/2
 * @UpdateUser: 0
 */
public class DaemonThread {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            Thread innerThread = new Thread(() -> {
                try {
                    for (int i = 1; i < 100; i++) {
                        Thread.sleep(1_000);
                        System.out.println("守护线程 " + i);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            innerThread.setDaemon(true);
            innerThread.start();
            try {
                for (int i = 1; i < 6; i++) {
                    Thread.sleep(1_000);
                    System.out.println("常规线程 " + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
