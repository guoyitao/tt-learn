package shujujiegou;

import cn.hutool.core.io.FileUtil;
import shujujiegou.job.ArrayWordsJob;
import shujujiegou.job.SortTreeWordsJob;
import shujujiegou.job.WordsJob;

import java.io.File;
import java.util.Scanner;

public class Main {
    static String INPUT = "C:\\mycode\\桌面快捷资料\\计量大学上课资料\\计量大学上课资料\\算法数据结构\\qimo8\\words.txt";
    static String OUTPUT = "C:\\mycode\\桌面快捷资料\\计量大学上课资料\\计量大学上课资料\\算法数据结构\\qimo8\\OutFile.txt";


    public static void main(String[] args) {
//        FileUtil.del(new File(OUTPUT));
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1、线性表\n2、二叉排序树\n3、退出系统\n请选择你需要的服务：输入数字（1-3）：\n");
            String input = scanner.next();
            if ("1".equals(input)) {
                run(new ArrayWordsJob(INPUT, OUTPUT, 5));
            } else if ("2".equals(input)) {
                run(new SortTreeWordsJob(INPUT, OUTPUT, 5));
            } else if ("3".equals(input)) {
                System.exit(123);
            }
        }
    }

    public static void run(WordsJob job) {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(
                    "\n1、连续执行完毕\n" +
                            "2、显示执行时间\n" +
                            "3、单步执行：识别并统计单词\n" +
                            "4、单步执行：删除并显示出现频率低的单词\n" +
                            "5、单步执行：输出其余单词及频率\n" +
                            "6、单步执行：计算并输出ASL值\n" +
                            "7、返回主菜单\n" +
                            "8、清空数据\n");
            String input = scanner.next();
            if ("1".equals(input)) {
                long start = System.currentTimeMillis();
                job.countWords();
                job.deleteLowWord(true);
                job.outputToFile();
                job.printASL(true);
                long end = System.currentTimeMillis();
                System.out.printf("执行时间：%d\n", end - start);
            } else if ("2".equals(input)) {
                long start = System.currentTimeMillis();
                job.countWords();
                job.deleteLowWord(false);
                job.outputToFile();
                job.printASL(false);
                long end = System.currentTimeMillis();
                System.out.printf("执行时间：%d\n", end - start);
            } else if ("3".equals(input)) {
                job.countWords();
            } else if ("4".equals(input)) {
                job.deleteLowWord(true);
            } else if ("5".equals(input)) {
                job.outputToFile();
            } else if ("6".equals(input)) {
                job.printASL(true);
            } else if ("7".equals(input)) {
                return;
            } else if ("8".equals(input)) {
                job.clear();
                FileUtil.del(new File(OUTPUT));
                System.out.println("清理成功！\n");
            }
        }
    }
}
