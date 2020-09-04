package com.guo.javaconcurrencyinpractice.fiveblockqueuefutruesemaphore;

import java.util.Random;
import java.util.concurrent.*;

/**
 *
 * 把所有考试的学生看做是一个DelayQueue，谁先做完题目释放谁
 * @Description:
 * @Author: guo
 * @CreateDate: 2019/4/6
 * @UpdateUser:
 */
class Student implements Runnable, Delayed {
    private String name;  //姓名
    private long costTime;//做试题的时间
    private long finishedTime;//完成时间

    public Student(String name, long costTime) {
        this.name = name;
        this.costTime = costTime;
        finishedTime = costTime + System.currentTimeMillis();
    }

    @Override
    public void run() {
        System.out.println(name + " 交卷,用时" + costTime / 1000);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return (finishedTime - System.currentTimeMillis());
    }

    @Override
    public int compareTo(Delayed o) {
        Student other = (Student) o;
        return costTime >= other.costTime ? 1 : -1;
    }

}

public class DelayQueueSchoolTest {
    static final int STUDENT_SIZE = 30;

    public static void main(String[] args) throws InterruptedException {
        Random r = new Random();
        //把所有学生看做一个延迟队列
        DelayQueue<Student> students = new DelayQueue<Student>();
        //构造一个线程池用来让学生们“做作业”
        ExecutorService exec = Executors.newFixedThreadPool(STUDENT_SIZE);
        for ( int i = 0; i < STUDENT_SIZE; i++) {
            //初始化学生的姓名和做题时间
            students.put( new Student( "学生" + (i + 1), 3000 + r.nextInt(10000)));
        }
        //开始做题
        while(! students.isEmpty()){
            exec.execute( students.take());
        }
        exec.shutdown();
    }
}
