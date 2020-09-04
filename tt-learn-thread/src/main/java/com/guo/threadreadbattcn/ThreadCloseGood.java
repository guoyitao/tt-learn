package com.guo.threadreadbattcn;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 完美版的线程关闭
 *  main线程监听executeService
 *  executeService里面的子线程才是真正执行任务的
 *
 *
 * @Description:
 * @Author: guo
 * @CreateDate: 2019/4/2
 * @UpdateUser: 3
 */
public class ThreadCloseGood {
    private Thread executeService;
    private AtomicBoolean finished = new AtomicBoolean(false);

    public static void main(String[] args) {
        ThreadCloseGood service = new ThreadCloseGood();
        long start = System.currentTimeMillis();
        //放入任务执行
        service.execute(() -> {
            try {
                Thread.sleep(3 * 1000);// TODO 模拟加载数据
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.listener(2 * 1000);
        //因为 间隔xxx  的监听所有可能顺序会不一样
        System.out.println("一共耗时：" + (System.currentTimeMillis() - start));
    }

    public void execute(Runnable task){
        executeService = new Thread(() ->{
            //创建守护线程
            Thread deamonThread = new Thread(task);
            deamonThread.setDaemon(true);
            deamonThread.start();
            try {
                //阻塞executeService,等deamonThread运行完了阻塞executeService才运行
                deamonThread.join();
                //完成
                finished.set(true);
            }catch (InterruptedException e){
                System.out.println("打断了真正工作的deamonThread");
            }

        });

        executeService.start();
    }

    //如果超过了
    public void listener(long mills){
        System.out.println("开启监听。。。。");
        long currentTime = System.currentTimeMillis();
        //如果还没结束就开始监听
        while (!finished.get()){
            //如果超时
            if ((System.currentTimeMillis() - currentTime) >= mills){
                System.out.println("工作耗时过长开始打断");
                //打断
                executeService.interrupt();
                break;
            }
            try {
                Thread.sleep(100L);//每隔100毫秒检测一次
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
