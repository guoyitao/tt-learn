package cainiaomianshi;

public class Thread交替打印 {


        static int i = 1;
        //锁
        static final Object lockObj = new Object();

        public static void main(String[] args) throws InterruptedException {

            new Thread(new Task()).start();
            // 让第二个线程启动
            Thread.sleep(100);
            new Thread(new Task()).start();

        }

    }


    class Task implements Runnable {

        @Override
        public void run() {
            while (true) {
                synchronized (Thread交替打印.lockObj) {
                    System.out.println(
                            Thread.currentThread().getName()+" : "+(Thread交替打印.i++));

                    Thread交替打印.lockObj.notifyAll();

                    try {
                        // 注意此操作会释放掉lockObj这个锁。
                        Thread交替打印.lockObj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // 我在这里休眠是为了提醒你，
                    // 只有synchronized释放掉锁，另一个线程才从有机会从wait()中被唤醒；
                    // 而不是notify()/notifyALl()后被就唤醒了
//                    try {
//                        Thread.sleep(3000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        }
    }