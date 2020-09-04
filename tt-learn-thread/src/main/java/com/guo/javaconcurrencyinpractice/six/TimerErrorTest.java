package com.guo.javaconcurrencyinpractice.six;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Description: timmer存在问题：如果一个任务抛出异常timer是无法捕捉的，会停止运行下面的任务并退出
 * @Author: guo
 * @CreateDate: 2019/4/8
 * @UpdateUser:
 */
public class TimerErrorTest {

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                throw new RuntimeException();
            }
        }, 1);
        Thread.sleep(1);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                throw new RuntimeException();
            }
        }, 1);
    }
}
