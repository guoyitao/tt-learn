package com.guo.threadreadbattcn;

/**
 * 死锁
 *
 * 如何出现 互相拿到对方的锁，循环等待
 *
 *
 * @Description:
 * @Author: guo
 * @CreateDate: 2019/4/2
 * @UpdateUser:
 */
public class DieLock {

    static void fun1(){
        while (true){
            synchronized (String.class){
                System.out.println(Thread.currentThread().getName() + "  get String.class LOCK");
                synchronized (Integer.class){
                    System.out.println(Thread.currentThread().getName() + "  get Integer.class LOCK");
                }
            }
        }
    }

    static void fun2(){
        while (true){
            synchronized (Integer.class){
                System.out.println(Thread.currentThread().getName() + "  get Integer.class LOCK");
                synchronized (String.class){
                    System.out.println(Thread.currentThread().getName() + "  get String.class LOCK");
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(DieLock::fun1,"fun1").start();
        new Thread(DieLock::fun2,"fun2").start();
    }
}
