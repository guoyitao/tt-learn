package com.guo.mashibing;

import java.util.LinkedList;

/**
 * 固定容量的同步容器，有put 和get ，getCount
 * 能支持2个生产者线程，10个消费者线程的阻塞调用
 *
 * wait和notify/notifyall实现
 *
 * @Description:
 * @Author: guo
 * @CreateDate: 2019/6/10
 * @UpdateUser:
 */
public class MyContainer1<T> {

    private final LinkedList<T> list = new LinkedList<>();
    private final int MAX = 10;
    private  int count = 0;


    public synchronized T put(T t){
        //满了
        //为什么需要while effective  java 说99.99%的情况wait都需要搭配while,
        // 因为被唤醒的线程可能有很多个，只需要一个线程生产一个产品，下一步就应该阻塞，
        // 如果是while的话其他线程还会检查一下有没有满，if的话就直接往下执行了，这样肯定会超过10个
        while (MAX == list.size()){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.addFirst(t);
        count++;

        //唤醒所有生产者
        this.notifyAll();
        return t;
    }

    public synchronized T get(){
//        空了已经取不出了
        T t = null;
        while (0 == list.size()){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = list.removeLast();
        count--;
        this.notifyAll(); //通知生产者消费

        return t;
    }

    public static void main(String[] args) {
        MyContainer1<Integer> continner1 = new MyContainer1<>();

        for (int i = 0; i <2 ; i++) {
            int finalI = i;
            new Thread(()->{
                while (true){
                    Integer put = continner1.put(finalI);
                    System.out.println(Thread.currentThread().getName()+ ": 生产" + put);
                }
            }).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while (true){
                    Integer integer = continner1.get();
                    System.out.println(Thread.currentThread().getName() + " : 消费---" +  integer);
                }
            }).start();
        }
    }


}
