package com.guo.mashibing;


import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

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
public class MyContainer2<T> {
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition producter = lock.newCondition();
    private final Condition consumer = lock.newCondition();
    private final LinkedList<T> list = new LinkedList<>();
    private final int MAX = 10;


    public T put(T t){
        try {
            lock.lock();
            while (MAX == list.size()){
                producter.await();
            }
            list.addFirst(t);
            consumer.signalAll();
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    public T get(){
        T t1 = null;
        try {
            lock.lock();
            while ( list.size() == 0){
                consumer.await();
            }
             t1 = list.removeFirst();
            producter.signalAll();
            return t1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    public static void main(String[] args) {
        MyContainer2<Integer> container2 = new MyContainer2<>();

        for (int i = 0; i <2 ; i++) {
            int finalI = i;
            new Thread(()->{
                while (true){
                    Integer put = container2.put(finalI);
                    System.out.println(Thread.currentThread().getName()+ ": 生产" + put);
                }
            }).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                while (true){
                    Integer integer = container2.get();
                    System.out.println(Thread.currentThread().getName() + " : 消费---" +  integer);
                }
            }).start();
        }
    }


}
