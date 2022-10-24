package chx.caozuoxitong;

import java.util.*;

/**
 * 题目2：进程调度2--最高响应比优先计算每个作业的周转时间
 * 问题描述：要求输入3个进程的信息，按照最高响应比优先的调度算法计算并输出每个进程的周转时间。(若两个进程的响应比相同，则优先选择先进入的进程。若两个进程的响应比相同，而且进入时刻也相同，则按照输入的顺序执行，如：P4和P6的响应比相同且进入时刻也相同，如P4先输入则选择P4先执行)
 *
 * 输入格式：程序要求输入3行，以回车符号作为分隔，每行有3个数据，以空格作为分隔。首先输入一个字符串（长度小于等于10），为进程名，第2个数据类型为整型，表示进程的进入时刻，第3个数据类型为整型，表示进程的运行时间。
 *
 * 输出格式：输出三个整数之间，整数之间用空格作为分隔，为每个进程的周转时间。
 *
 * 样例输入1：
 * P1 1 1
 * P2 2 2
 * P3 3 3
 * 样例输出1：
 * 1 2 4
 */
public class Work2 {
    public static void main(String[] args) {
        List<Process> processes = new ArrayList<>();
        int runTime = 0; //开始时间
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            Process process = new Process();
            processes.add(process);
            String[] items =  scanner.nextLine().split(" ");
            process.name = items[0];
            process.join = Integer.parseInt(items[1]);
            process.time = Integer.parseInt(items[2]);
        }
        processes.sort(new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                return o1.join.compareTo(o2.join);
            }
        });

        for (int i = 0; i <= 2; i++) { //3个任务要执行
            Process remove = processes.remove(0);
            final int finalAvgTime = runTime;
            if (runTime < remove.join){
                runTime += remove.join;
            }
            runTime += remove.time; //等待时间+需要执行时间
            System.out.println(runTime- remove.join);
            processes.sort(new Comparator<Process>() {
                @Override
                public int compare(Process o1, Process o2) {
                    int a = -priority(o1, o1.time - finalAvgTime).compareTo(priority(o2, o2.time - finalAvgTime));
                    if (a == 0){
                        a = o1.join.compareTo(o2.join);
                    }
                    if (a == 0){
                        a = o1.name.compareTo(o2.name);
                    }
                    return a;
                }
            });
        }

    }

    //响应比计算  （等待时间+要求服务时间）/要求服务时间
    public static Double priority(Process process, double waitTime){
        return (waitTime + process.time) / process.time;
    }

    public static class Process{
        String name;
        Integer join;
        Integer time;
    }
}
