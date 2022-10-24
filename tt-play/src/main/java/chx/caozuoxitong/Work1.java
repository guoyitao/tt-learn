package chx.caozuoxitong;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 题目1：进程调度1—静态非剥夺式优先级调度计算平均作业周转时间
 * 问题描述：要求输入3个进程的信息，假设这些进程均是在0时刻同时到达，若进程调度采用非剥夺式静态优先级（优先数数值大的表示优先级比较高；如果遇到优先级一样，按照输入顺序执行。），计算并输出平均作业周转时间。
 *
 * 输入格式：程序要求输入3行，以回车符号作为分隔，每行有3个数据，以空格作为分隔。首先输入一个字符串（长度小于等于10），为进程名，第2个数据类型为整型，表示进程的优先数，第3个数据类型为整型，表示进程的运行时间。
 *
 * 输出格式：输出结果为一个浮点数，保留到小数点后一位，为系统的平均作业周转时间。
 *
 * 样例输入1：
 * P1 1 1
 * P2 2 2
 * P3 3 3
 * 样例输出1：
 * 4.7
 */
public class Work1 {


    public static void main(String[] args) {
        //定义数组用来接收3个进程对象
        Process[] processes = new Process[3];

        //输入进程信息
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < processes.length; i++) {
            //创建进程对象放进数组里面
            Process process = new Process();
            processes[i] = process;
            //把输入进程的信息，用字符串分割函数分割出进程的名字,优先级,运行时间
            String[] items =  scanner.nextLine().split(" ");
            process.name = items[0];
            process.priority = Integer.parseInt(items[1]);
            process.time = Float.parseFloat(items[2]);
        }
        //排序 从大到小
        Arrays.sort(processes, new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return -o1.priority.compareTo(o2.priority);
            }
        });

        float turnoverTime = 0;//周转时间
        //3个进程总共作业周转时间
        float sumTime = 0;
        for(int i=0;i<processes.length;i++)
        {
            for (int j = 0; j <= i; j++) {
                //(完成时间+等待时间) - 到达系统时间(这里在0时刻到达可以不用减去达到时间)
                turnoverTime += processes[j].time;

            }

            sumTime+=turnoverTime;
            turnoverTime = 0;//清0
        }
        //平均周转时间
        System.out.printf("%.1f",sumTime/processes.length);
    }

    public static class Process{
        String name;
        Integer priority;
        Float time;
    }
}
