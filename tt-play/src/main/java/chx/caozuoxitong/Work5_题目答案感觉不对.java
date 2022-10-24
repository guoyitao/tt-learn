package chx.caozuoxitong;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * 题目5：死锁—利用银行家算法判断系统的安全性
 * 问题描述：假设系统中有A、B、C三类资源，且有四个并发进程，要求输入资源总量Resource，以及每个进程运行所需的资源总量Claim和已经分配得到的资源量Allocation，利用银行家算法判断当前状态是否为安全状态，若为安全状态则给出一个安全序列。
 *
 * 输入格式：程序要求输入五行，以回车符号作为分隔。第一行是三个整数，整数之间以空格作为分隔，表示A、B、C三类资源的总量。下面的四行分别表示每个进程运行所需的资源总量Claim和已经分配得到的资源量Allocation；每行有7个数据，以空格作为分隔。首先输入一个字符串（长度小于等于10），为进程名；第2、3、4个数据类型为整型，表示相应进程运行所需A、B、C三种资源总量Claim；第5、6、7个数据类型为整型，表示相应进程已经分配得到的A、B、C三种资源量Allocation。
 *
 * 输出格式：输出一个字符串。若当前为不安全状态则输出为”false”（不含双引号，所有字母皆为小写）；若当前为安全状态则输出一个安全序列，进程名之间用空格作为分隔）。
 *
 * 样例输入1：
 * 9 5 7
 * P1 5 3 4 2 1 3
 * P2 9 5 2 2 1 1
 * P3 3 2 2 2 2 1
 * P4 6 4 1 1 1 1
 * 样例输出1：
 * P3 P1 P4 P2
 */
public class Work5_题目答案感觉不对 {
    public static void main(String[] args) {
        List<Process> processes = new ArrayList<>();
        Integer[] abc = new Integer[3]; //MAX资源
        Scanner scanner = new Scanner(System.in);
        String[] abcStr = scanner.nextLine().split(" ");
        for (int i = 0; i < 3; i++) {
            abc[i] = Integer.parseInt(abcStr[i]);
        }
        for (int i = 0; i < 4; i++) {
            Process process = new Process();
            String[] proStr = scanner.nextLine().split(" ");
            process.name = proStr[0];
            for (int j = 1; j <= 3; j++) {
                process.Claim[j-1] = Integer.valueOf(proStr[j]);
            }
            for (int j = 4; j < 7; j++) {
                process.Allocation[j-4] = Integer.valueOf(proStr[j]);
            }
            processes.add(process);
        }

        //计算出 减去已经占用的 还剩下多少可以用资源abc  Avail
        for (Process process : processes) {
            for (int i = 0; i < abc.length; i++) {
                abc[i] -= process.Allocation[i];
            }
        }
//        //看看是不是刚开始就已经不安全了
//        for (int i = 0; i < abc.length; i++) {
//            if (abc[i]< 0) {
//                System.out.println("false");
//                return;
//            }
//        }
        //运行顺序
        int countOrder = 1;
        for (int i = 0; i < processes.size(); i++) {
            for (int j = 0; j < processes.size(); j++) {
                Process processJ = processes.get(j);
                if (processJ.finished) { //已经完成的就别看了
                    continue;
                }
                if (smallThan(processJ.Claim, abc)) { //可以分配给这个进程
                    //释放资源还给abc
                    for (int a = 0; a < processJ.Allocation.length; a++) {
                        abc[a] += processJ.Allocation[a];
                    }
                    processJ.finished = true; //成功完成任务
                    processJ.order = countOrder++;
                    break;
                }
            }
        }
        boolean safe = true;
        for (Process process : processes) {
            if (!process.finished){
                safe = false;
            }
        }
        //安全就输出运行顺序
        if (safe){
            processes.sort(new Comparator<Process>(){
                @Override
                public int compare(Process o1, Process o2) {
                    return o1.order.compareTo(o2.order);
                }
            });
            for (Process process : processes) {
                System.out.print(process.name + "");
            }
        }else{
            System.out.println("false");
        }


    }

    public static boolean smallThan(Integer[] a, Integer[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] > b[i]) { //a有一个大于b就说明 a>b
                return false;
            }
        }
        return true;
    }


    public static class Process{
        String name;
        Integer[] Claim= new Integer[3];//进程运行所需的资源总量
        Integer[] Allocation= new Integer[3];//已经分配得到的资源量

        boolean finished = false;

        Integer order;

    }
}
