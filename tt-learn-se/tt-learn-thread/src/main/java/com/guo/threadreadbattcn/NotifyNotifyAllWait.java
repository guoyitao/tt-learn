package com.guo.threadreadbattcn;


import java.util.stream.Stream;

/**
 * wait(): 使当前线程处于等待状态，直到另外的线程调用notify或notifyAll将它唤醒  （自动释放锁）
 * notify(): 唤醒该对象监听的其中一个线程（规则取决于JVM厂商，FILO,FIFO,随机…）
 * notifyAll(): 唤醒该对象监听的所有线程
 *
 * 锁池： 假设T1线程已经拥有了某个对象(注意:不是类)的锁，而其它的线程想要调用该对象的synchronized方法(或者synchronized块)，
 * 由于这些线程在进入对象的synchronized方法之前都需要先获得该对象的锁的拥有权，但是该对象的锁目前正被T1线程拥有，所以这些线程就进入了该对象的锁池中。
 *
 * 等待池： 假设T1线程调用了某个对象的wait()方法，T1线程就会释放该对象的锁(因为wait()方法必须出现在synchronized中，
 * 这样自然在执行wait()方法之前T1线程就已经拥有了该对象的锁)，同时T1线程进入到了该对象的等待池中。
 * 如果有其它线程调用了相同对象的notifyAll()方法，那么处于该对象的等待池中的线程就会全部进入该对象的锁池中，从新争夺锁的拥有权。
 * 如果另外的一个线程调用了相同对象的notify()方法，那么仅仅有一个处于该对象的等待池中的线程(随机)会进入该对象的锁池.
 *
 * 注意{
 *    在调用wait(), notify()或notifyAll()的时候，都必须获得某个对象(注意:不是类)的锁。
 *    永远在循环（loop）里调用 wait 和 notify，而不是在 If 语句中
 *    永远在synchronized的函数或对象里使用wait、notify和notifyAll，不然Java虚拟机会生成 IllegalMonitorStateException。
 * }
 *
 *
 * @Description:
 * @Author: guo
 * @CreateDate: 2019/4/2
 * @UpdateUser:
 */
public class NotifyNotifyAllWait {

    private final static byte[] LOCK = new byte[0];//定义一个锁对象
    private static boolean isProduction = true;//消息投递
    private static int i = 0;//生产的消息

    static void product(){
        synchronized (LOCK){
            try {
                Thread.sleep(100);
                if (isProduction){
                    System.out.println("P->" + i++);
                    isProduction =false;
                    //只唤醒一个消费者
                    LOCK.notify();
                }else{
                    LOCK.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static void constomer(){
        synchronized (LOCK){
            try {
                Thread.sleep(100);
                if (!isProduction){
                    System.out.println("c->" + i);
                    isProduction =true;
                    LOCK.notify(); //随机从等待池拿出一个   消费者 或者是 生产者  这时候如果拿出了一个消费者，那商品还没生产出来，而这个消费者又无法生产，就会发生卡主
//                    LOCK.notifyAll();  //notifyAll() 直接唤醒LOCK对象里面等待池 里面所有的线程
                }else{
                    LOCK.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //多对多会出现bug
    static void bug(){
        Stream.of("P1", "P2", "P3", "P4").forEach(name -> new Thread(() -> {
            while (true) {
                product();
            }
        }, name).start());
        Stream.of("C1", "C2").forEach(name -> new Thread(() -> {
            while (true) {
                constomer();
            }
        }, name).start());
    }
    //
    static void oneToOne(){
        new Thread(() -> {
            while (true) {
                product();
            }
        }).start();
        new Thread(() -> {
            while (true) {
                constomer();
            }
        }).start();
    }


    public static void main(String[] args) {
//        oneToOne();
        bug();
    }
}
