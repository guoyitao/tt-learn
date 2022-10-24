package chx.caozuoxitong;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * 题目12：驱动调度—采用电梯调度算法排列出磁盘请求响应次序
 * 问题描述：要求输入一个柱面访问请求序列以及当前磁头所在柱面号和移动方向，输出采用电梯调度算法时移动臂响应的柱面访问序列。
 *
 * 输入格式：程序要求输入3行，以回车符号作为分隔，第一行是2个整数n、m，之间用空格隔开，n表示当前磁头所在的柱面号；m表示第二行输入m个数；第二行是m个整数，数之间以空格作为分隔，表示柱面访问请求序列；第三行是数字-1或1，当为-1时表示移动臂向柱面号减小方向移动，当为1时表示移动臂向柱面号增大方向移动。
 *
 * 输出格式：输出m个整数，数之间以空格作为分隔，采用电梯调度算法时移动臂响应的柱面访问序列。
 *
 * 样例输入1：
 * 15 10
 * 24 38 2 110 43 36 5 11 6 180
 * -1
 * 样例输出1：
 * 11 6 5 2 24 36 38 43 110 180
 */
public class Work12 {
    public static void main(String[] args) {
        List<DiskItem> disk = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int m,n;
        int way; //-1或1，当为-1时表示移动臂向柱面号减小方向移动，当为1时表示移动臂向柱面号增大方向移动
        String[] mn = scanner.nextLine().split(" ");
        n = Integer.parseInt(mn[0]);
        m = Integer.parseInt(mn[1]); //start位置

        String[] jobsStr = scanner.nextLine().split(" ");
        for (int i = 0; i < m; i++) {
            DiskItem item = new DiskItem();
            disk.add(item);
            item.num = Integer.valueOf(jobsStr[i]);
        }
        way = Integer.parseInt(scanner.nextLine());

        disk.sort(new Comparator<DiskItem>() {
            @Override
            public int compare(DiskItem o1, DiskItem o2) {
                return o1.num.compareTo(o2.num);
            }
        });

        //找到开始的位置
        int startIdx = -1;
        for (int i = 0; i < disk.size(); i++) {
            if (disk.get(i).num > n){
                startIdx = i -1;
                break;
            }
        }

        int countHand = disk.size(); //需要访问的次数
        while (countHand != 0) {
            DiskItem item = disk.get(startIdx);
            if (item.isVisited){ //不访问直接移动到下一个
                startIdx += way;
                continue;
            }

            //访问
            System.out.print(item.num + " ");
            item.isVisited = true;
            startIdx += way;
            if (startIdx == 0 || startIdx == disk.size() - 1) { //到了头或者尾巴之后  往回走
                way = -way;
            }
            countHand--;

        }
    }

    static class DiskItem {
        boolean isVisited = false;
        Integer num;
    }
}
