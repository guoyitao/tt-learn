package com.guo.javaconcurrencyinpractice.eightThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description: 创建不能修改配置的ThreadPool
 * @Author: guo
 * @CreateDate: 2019/4/12
 * @UpdateUser:
 */
public class CreateUnConfigThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        //包装成不能修改配置的
        ExecutorService exec = Executors.unconfigurableExecutorService(executorService);

        if (exec instanceof ThreadPoolExecutor){
            System.out.println("ThreadPoolExecutor exec");
        }

        if (executorService instanceof ThreadPoolExecutor){
            System.out.println("ThreadPoolExecutor executorService");
            ThreadPoolExecutor executor = (ThreadPoolExecutor) executorService;
            executor.setCorePoolSize(100);
        }

    }
}
