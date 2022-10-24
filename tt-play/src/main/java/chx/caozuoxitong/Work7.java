package chx.caozuoxitong;

import java.util.Scanner;

/**
 * 题目7：存储管理—FIFO页面替换算法计算中断次数
 * 问题描述：在请求分页式存储管理方式中，要求输入一个对5个页面的访问序列，输出当系统分配给进程物理页框数为m个时，按照FIFO页面替换算法的缺页中断次数（假设初始时页框均为空）。
 *
 * 输入格式：程序要求输入3行，以回车符号作为分隔，第一行是一个整数n，表示页面访问序列中访问页面的次数；第二行是n个整数，数之间以空格作为分隔，表示页面访问序列。第三行是一个整数m，表示系统分配给进程物理页框数。
 *
 * 输出格式：输出一个整数，表示缺页中断次数。
 *
 * 样例输入1：
 * 12
 * 4 3 2 1 4 3 5 4 3 2 1 5
 * 3
 * 样例输出1：
 * 9
 */
public class Work7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int[] job = new int[n]; //页面访问序列
        String[] s = scanner.nextLine().split(" ");
        for (int i = 0; i < job.length; i++) {
            job[i] = Integer.parseInt(s[i]);
        }

        int m = Integer.parseInt(scanner.nextLine());//表示系统分配给进程物理页框数
        int[] freePartition = new int[m];

        int countSwap = 0; //缺页中断次数
        int idxFreePartition = 0; //内存的索引
        for (int i : job) {
            boolean in = false; //是否已经在内存
            for (int inMemory : freePartition) {
                if (inMemory == i){
                    in = true;
                    break;
                }
            }

            if (!in){//需要页面置换
                countSwap++;
                freePartition[idxFreePartition++] = i;
                if (idxFreePartition >= freePartition.length){
                    idxFreePartition = 0;
                }
            }

        }
        System.out.println(countSwap);
    }

}
