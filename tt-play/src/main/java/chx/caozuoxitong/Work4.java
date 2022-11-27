package chx.caozuoxitong;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 题目4：进程调度4----时间片轮转
 *
 * 问题描述：要求输入N个进程（0<N<=100），输入时间片M（0<M〈=5），按照进程输入的顺序以时间片轮转的方法输出指定的第K轮（K>0）执行的那个进程的进程名。
 *
 * 输入格式：程序首先输入一个正整数M（0<M〈=5）作为时间片，下一行输入一个正整数N（0<N<=100），接下来输入为N行，以回车符号作为分隔，每行有2个数据，以空格作为分隔。第一个数据是字符串（长度小于等于10），该字符串为进程名，第2个数据类型为整型，表示该进程需要的运行时间。最后输入一个正整数K，作为时间片轮转的次数（次数从1开始计数）。
 *
 * 输出格式：输出一个字符串，为最后执行进程的进程名；若无进程运行，则输出“over”（不含双引号，所有字母皆为小写）。
 * 样例输入1：
 * 1
 * 3
 * P1 1
 * P2 2
 * P3 3
 * 3
 * 样例输出1：P3
 */
public class Work4 {
    public static void main(String[] args) {
        List<Process> processes = new ArrayList<>();
        int n;
        int m;
        int k;
        Scanner scanner = new Scanner(System.in);
        m = Integer.parseInt(scanner.nextLine());//时间片
        n = Integer.parseInt(scanner.nextLine());//N个进程
        for (int i = 0; i < n; i++) {
            Process process = new Process();
            String s = scanner.nextLine();
            String[] items =  s.split(" ");
            process.name = items[0];
            process.time = Integer.valueOf(items[1]);
            processes.add(process);
        }
        k = Integer.parseInt(scanner.nextLine());//时间片轮转的次数（次数从1开始计数）
        int index = 0;
        int count = 0; //轮转的次数
        while(processes.size() != 0){
            count++;
            Process process = processes.get(index);
            if (count == k){
                //轮转次数到了，可以得到答案了
                System.out.print(process.name);
                return;
            }
            process.time -= m;
            System.out.printf("[%s]",process.name);
            if (process.time <= 0){ //运行完了就删除这个进程
                processes.remove(index);
            }else{
                index++;
            }
            if (index == processes.size()){ //轮转到最后一个进程就重新从第一个开始
                index = 0;
            }
        }
        System.out.print("over");

    }

    public static class Process{
        String name;
        Integer time;
    }
}
