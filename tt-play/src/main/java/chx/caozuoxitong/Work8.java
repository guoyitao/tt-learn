package chx.caozuoxitong;

import java.util.Scanner;

/**
 * 题目8：存储管理1
 * 问题描述：现有一个8*8的存储器，要对其空间进行分配。（下标从0开始，最后一个内存块下标为63）。现已有块号为1、7、13、23、47、59的几个内存块被占用。现操作系统要求申请N块内存空间（0<N<=64），当输入的块数N超出其剩余空闲块数的时候，输出为“false”，当输入为合理范围的时候，就输出其以行主序分配的最后一个内存空间的下标。
 *
 * 输入格式：程序要求输入一个整型数N，表示要申请分配空间的大小。
 *
 * 输出格式：输出为一个整型数，表示最后一个被分配空间的下标。
 *
 * 样例输入1：
 * 3
 * 样例输出1：
 * 3
 */
public class Work8 {
    public static void main(String[] args) {
        boolean[] memory = new boolean[64];
        memory[1] = true;
        memory[7] = true;
        memory[13] = true;
        memory[23] = true;
        memory[47] = true;
        memory[59] = true;
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine()); //剩余需要 申请分配空间的大小
        int i = 0;
        for (; i < memory.length; i++) {
            if (memory[i]) { //已结分配了就跳过
                continue;
            }
            memory[i] = true; //分配
            n--;
            if (n == 0){
                break; //分配完毕
            }
        }
        System.out.println(i);

    }
}
