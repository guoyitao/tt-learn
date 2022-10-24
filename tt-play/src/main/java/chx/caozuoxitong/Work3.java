package chx.caozuoxitong;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * 题目3：进程调度3
 * 问题描述：要求输入N个进程（N为正整型数，0<N<=25535），输出按照优先级从高到低执行的进程名字符串序列,直至结束。（如果遇到优先级一样，按照输入顺序先后执行。），本题中，优先数数值较高的进程，优先级也较高。
 *
 * 输入格式：程序首先要求输入一个整型变量N，接下来输入为N行，以回车符号作为分隔，每行有3个数据，以空格作为分隔。首先输入一个字符串（长度小于等于10），该字符串为进程名。第2个数据类型为整型，表示进程的优先数。第3个数据类型为整型，表示进程的运行时间。
 *
 * 输出格式：输出1行，M个字符串，字符串之间用空格作为分隔。
 *
 * 样例输入1：
 * 3
 * P1 1 1
 * P2 2 2
 * P3 3 3
 * 样例输出1：
 * P3 P2 P3 P1 P2 P3
 */
public class Work3 {
    public static void main(String[] args) {
        List<Process> processes = new ArrayList<>();
        int runTime = 0; //开始时间
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < count; i++) {
            Process process = new Process();
            processes.add(process);
            String s = scanner.nextLine();
            String[] items =  s.split(" ");
            process.name = items[0];
            process.priority = Integer.parseInt(items[1]);
            process.time = Integer.parseInt(items[2]);
        }
        processes.sort(new Comparator<Process>() {
            @Override
            public int compare(Process o1, Process o2) {
                int i = -o1.priority.compareTo(o2.priority); //按照优先级排序
                if (i == 0){
                    i = o1.name.compareTo(o2.name); //按照输入顺序排
                }
                return i ;
            }
        });

        for (;  processes.size() != 0; ) {
            Process remove = processes.get(0);
            remove.time--;
            remove.priority--;
            if (remove.time == 0){//这个进程运行完毕
                processes.remove(0);
            }
            System.out.print(remove.name + " ");
            processes.sort(new Comparator<Process>() {
                @Override
                public int compare(Process o1, Process o2) {
                    int i = -o1.priority.compareTo(o2.priority); //按照优先级排序
                    if (i == 0){
                        i = o1.name.compareTo(o2.name); //按照输入顺序排
                    }
                    return i ;
                }
            });
        }
    }

    public static class Process{
        String name;
        Integer priority;
        Integer time;
    }
}
