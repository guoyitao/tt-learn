package linklist;

import com.google.common.base.Enums;

/**
 * description: 约瑟夫问题丢手帕问题
 * josephu问题为:设编号为1,2,…n的n个人围坐一圈,约定编号为k(1==k<=n)的人从1开始报数,数
 * 到m的那个人出列,它的下一位又从1开始报数,数到m的那个人又出列,依次类推,直到所有人出列为止
 * 由此产生一个出队编号的序列。
 * author: Guo Yitao
 * create: 2020-07-24 19:19
 **/
public class Josepfu {
    private static class Boy {
        int no;
        Boy next;

        public Boy(int no) {
            this.no = no;
        }

        @Override
        public String toString() {
            return "Boy{" + "no=" + no + '}';
        }
    }

    //环形单向链表
    private static class CricleSingleLinkedList {
        private Boy first;
        private int nums = 0;

        public void addBoys(int nums) {
            this.nums = nums;
            if (nums < 2) {
                System.out.println("输入nums错误");
                return;
            }
            Boy curBoy = null;
            for (int i = 1; i <= nums; i++) {
                Boy boy = new Boy(i);
                if (i==1){
                    first = boy;
                    first.next = first;
                    curBoy = first;
                }else {
                    curBoy.next = boy;
                    boy.next = first;
                    curBoy = boy;
                }
            }
        }

        public void list(){
            if (first == null){
                return;
            }
            Boy tmp = first;
            while (tmp!=null){
                System.out.println(tmp);
                tmp = tmp.next;
                if (tmp.no ==1){
                    return;
                }
            }
        }
        /*
         * @date 2020/7/25 11:28
         * @author guoyitao
         * @param start 从第几个小孩开始
         * @param a 表示数几下
         * @return void
         */
        public void getout(int start,int a){
            if (first == null || start < 1 || start > nums || a <2){
                System.out.println("getout：参数输入有误");
            }
            Boy helper = first;
            //helper先指向最后一个节点
            while (true){
                if (helper.next == first) {
                    break;
                }
                helper = helper.next;
            }
            //从start开始
            for (int i = 0; i < start -1; i++) {
                first = first.next;
                helper = helper.next;
            }
            //出圈 移动a-1次，出一下
            while (true){
                if (helper == first){
                    System.out.println("出圈完毕剩余" + helper);
                    break;
                }
                //移动a-1下
                for (int i = 0; i < a -1; i++) {
                    first = first.next;
                    helper = helper.next;
                }
                //出
                System.out.println("get out ：" + first);
                first = first.next;
                helper.next = first;
            }
        }



    }

    public static void main(String[] args) {
        CricleSingleLinkedList linkedList = new CricleSingleLinkedList();
        linkedList.addBoys(5);
        linkedList.list();

        linkedList.getout(1,2);
    }
}
