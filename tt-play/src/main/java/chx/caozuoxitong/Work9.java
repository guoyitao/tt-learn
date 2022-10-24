package chx.caozuoxitong;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 题目9：存储管理2
 * 问题描述：现有一个8*8的存储器，要对其空间进行分配。（下标从0开始，最后一个内存块下标为63）。现已有块号为2、7、13、23、37、47、59、61的几个内存块被占用。要求输入需分配的进程数M（0<M<=56），接下来输入为M个整型数，每个数为各个进程需占用的内存块数。当分配到某进程时，其剩余空闲块数可以分配，就输出当前进程分配的最后一个内存空间的下标。当分配到某进程时，其进程块数超出剩余空闲块数无法分配，输出为“false”（不含双引号，且为全小写）。输出的多个下标（或"false"）之间用空格隔开。
 *
 * 输入格式：程序输入分为两行，第一行要求输入一个整型数M，表示要所需分配空间的进程数，接下来的第二行输入M个整型数，每个数之间用空格隔开，表示M个进程每个进程占用的内存空间大小。
 *
 * 输出格式：输出为M组整型数（或"false"），每个整型数表示该进程最后一个被分配的内存空间的下标（或"false"），下标（或"false"）之间用空格隔开。
 *
 * 样例输入1：
 * 3
 * 3 3 3
 * 样例输出1：
 * 3 6 10
 */
public class Work9 {
    public static void main(String[] args) {
        boolean[] memory = new boolean[64];
        memory[2] = true;
        memory[7] = true;
        memory[13] = true;
        memory[23] = true;
        memory[47] = true;
        memory[59] = true;
        memory[61] = true;
        Scanner scanner = new Scanner(System.in);
        List<Process> processes = new ArrayList<Process>();
        int n = Integer.parseInt(scanner.nextLine()); //进程数量
        String[] s = scanner.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            Process process = new Process();
            processes.add(process);
            process.need = Integer.parseInt(s[i]);
            process.end = -1;
        }

        int handleIdx = 0; //正在处理的进程索引
        for (int i = 0; i < memory.length; i++) {
            if (memory[i]) { //已结分配了就跳过
                continue;
            }
            Process process = processes.get(handleIdx);
            process.need--; //分配掉一个单位
            memory[i] = true;
            if (process.need == 0){
                //分配完了准备处理下一个进程
                process.end = i;
                handleIdx++;
                if (handleIdx > processes.size() - 1){
                    break;
                }
            }
        }
        //判断是否成功
        boolean success = true;
        for (Process process : processes) {
            if (process.end == 0){
                success = false;
            }
        }
        //输出
        if (!success){
            System.out.println("false");
            return;
        }
        for (Process process : processes) {
            System.out.print(process.end + " ");
        }

    }

    static class Process{
        int need;
        int end; //最后一个被分配的内存空间的下标
    }
}
