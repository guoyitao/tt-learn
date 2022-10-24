package chx.caozuoxitong;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 题目6：存储管理—可变分区存储管理方式的最佳适应分配算法
 * 问题描述：当内存管理采用可变分区分配方案时，要求输入多个空闲分区和进程内存请求序列，输出显示采用最佳适应分配算法分配给各个进程的分区编号。
 *
 * 输入格式：程序要求输入3行，以回车符号作为分隔，第一行是一个整数n（n>=3），表示空闲分区的数量；第二行是n个整数，依次按地址递增对应第一行n个空闲分区的存储容量，每个整型数的数值代表所对应空间的剩余存储容量。n个分区（分区按地址递增依次从1开始编号，若分区X被切割分配了，剩余部分即使为0也保留原来的分区编号X）。第三行是3个整数，两个数之间以空格作为分隔，分别表示三个进程先后依次申请的内存空间的大小。
 *
 * 输出格式：输出一行三个整数，整数之间用空格作为分隔，分别表示三个进程所分配的分区编号；若分配失败，则用”false”表示（不含双引号，所有字母皆为小写）。
 *
 * 样例输入1：
 * 6
 * 20 5 6 18 60 4
 * 12 7 20
 * 样例输出1：
 * 4 1 5
 */
public class Work6 {
    public static void main(String[] args) {
        Job[] job = new Job[3]; //需要分配的任务
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        FreePartition[] freePartition = new FreePartition[n]; //空闲分区
        String[] s = scanner.nextLine().split(" ");
        for (int i = 0; i < freePartition.length; i++) {
            freePartition[i] = new FreePartition();
            freePartition[i].free = Integer.parseInt(s[i]);
            freePartition[i].index = i+1;
        }
        s = scanner.nextLine().split(" ");
        for (int i = 0; i < job.length; i++) {
            job[i] = new Job();
            job[i].job = Integer.parseInt(s[i]);
        }
        Arrays.sort(freePartition, new Comparator<FreePartition>() {
            @Override
            public int compare(FreePartition o1, FreePartition o2) {
                return o1.free.compareTo(o2.free);
            }
        });

        for (int j = 0; j < job.length; j++) {
            boolean success = false;
            for (int i = 0; i < freePartition.length; i++) {
                if (job[j].job <= freePartition[i].free ){
                    freePartition[i].free -= job[j].job; //分配
                    job[j].index = freePartition[i].index; //记录是被分配 在哪个分区？
                    success = true;
                    break;
                }
            }

            if (!success){
                //分配失败
                System.out.println("false");
                return;
            }
        }

        for (Job i : job) {
            System.out.print(i.index + " ");
        }


    }

    static class Job{
        Integer index;
        Integer job;
    }

    static class FreePartition{
        Integer index;
        Integer free;
    }
}
