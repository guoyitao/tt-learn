package com.guo.threadreadbattcn;

/**
 * @Description: 使用对象当做锁   如果使用.class当锁，效果和在static function一样
 * @Author: guo
 * @CreateDate: 2019/4/2
 * @UpdateUser:
 */
public class SynchroizedDemo3  implements  Runnable{
    private final byte[] LOCK = new byte[0];

    @Override
    public void run() {
        synchronized (LOCK){
            System.out.println("查询数据"  + Thread.currentThread().getName());
            System.out.println("开始转账：" + Thread.currentThread().getName());
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("转账完毕");
        }
    }

    public static void main(String[] args) {
        SynchroizedDemo3 synchroizedDemo3 = new SynchroizedDemo3();
        new Thread(synchroizedDemo3,"杭州").start();
        new Thread(synchroizedDemo3,"杭州").start();
        new Thread(synchroizedDemo3,"杭州").start();
    }
}
