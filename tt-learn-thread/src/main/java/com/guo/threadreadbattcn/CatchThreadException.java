package com.guo.threadreadbattcn;


/**
 * @Description: 线程的异常处理器
 * @Author: guo
 * @CreateDate: 2019/4/3
 * @UpdateUser:
 */
public class CatchThreadException {
    static class ConnectionPool {
        static void create() {
            System.out.println("初始化连接池...");
        }
        static void close() {
            System.out.println("关闭连接池...");
        }
    }

    static void ok(){
        ConnectionPool.create();

        Thread thread = new Thread(() ->{
            System.out.println("run................" + 0/0);

        });
        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName() + e.toString());
                ConnectionPool.close();
            }
        });
        thread.start();
    }
    //这样的话是捕获main线程的异常，是抓不到thread里面的异常的
    static void notOk(){
        ConnectionPool.create();
        try {
            //有个任务需要异步执行
            Thread thread = new Thread(() -> System.out.println(Integer.parseInt("ABC")), "T2");
            thread.start();
        } catch (Exception e) {
            ConnectionPool.close();
        }
    }

    public static void main(String[] args) {
//        ok();
        notOk();

    }
}
