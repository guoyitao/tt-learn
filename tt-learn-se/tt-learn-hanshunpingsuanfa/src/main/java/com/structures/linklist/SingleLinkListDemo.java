package com.structures.linklist;

import java.util.Stack;

/**
 * description: 单链链表
 * author: Guo Yitao
 * create: 2020-07-22 16:08
 **/
public class SingleLinkListDemo {
    private static class HeroNode{
        int no; //编号
        String name;
        HeroNode next; //下个节点

        public HeroNode(int no, String name, HeroNode next) {
            this.no = no;
            this.name = name;
            this.next = next;
        }

        @Override
        public String toString() {
            return "HeroNode{" + "no=" + no + ", name='" + name + '}';
        }
    }

    private static class SingleLinkList{
        private HeroNode head = new HeroNode(0,"",null);

        public HeroNode add(HeroNode node){
            HeroNode tmp = head;
            //遍历找到最后一个节点
            while (true){
                if (tmp.next==null) {
                    break;
                }
                tmp = tmp.next;
            }
            tmp.next = node;
            return tmp.next;
        }

        //打印节点
        public void list(){
            System.out.println("-----------------------");
            if (head.next == null){
                System.out.println("链表为空");
                return;
            }
            HeroNode tmp = head.next;
            while (true){
                if (tmp == null){
                    break;
                }
                System.out.println(tmp);
                tmp = tmp.next;
            }
            System.out.println("-----------------------");
        }

        public void update(HeroNode heroNode){
            boolean flag = false; //默认没找到
            HeroNode tmp = head.next;
            while (true){
                if (tmp.next==null) {
//                    没找到
                    break;
                }
                if (tmp.no == heroNode.no){
//                    找到
                    flag = true;
                    break;
                }
                tmp = tmp.next;
            }
            if (flag){
                tmp.name = heroNode.name;
            }else {
                System.out.println("Node not found！");
            }

        }




        //顺序插入
        public void addByOrder(HeroNode heroNode){
            HeroNode tmp = this.head;
            boolean flag = false; //是否加入
            while (true){
                //找到了，已经遍历到最后
                if (tmp.next == null) {
                    break;
                }
                if (tmp.next.no > heroNode.no ){
                    //找到了，当前加入的元素比后面小
                    break;
                }else if (tmp.next.no == heroNode.no){
                    flag = true;
                    break;
                }
                tmp = tmp.next;
            }
            if (flag){
                System.out.println( heroNode+"  的编号已经存在" );
            }else {
                heroNode.next = tmp.next;
                tmp.next = heroNode;
            }
        }
        /*
         * @date 2020/7/22 18:09
         * @author guoyitao
         * @param delNode
         * @return void
         */
        public void remove(int no){
            HeroNode tmp = head;
            boolean flag = false;
            while (true){
                //已经遍历到最后
                if (tmp.next == null) {
                    break;
                }
                if (tmp.next.no == no){
                    //找到了要删除的
                    flag = true;
                    break;
                }
                tmp = tmp.next;
            }
            if (flag){
                tmp.next  = tmp.next.next;
            }else {
                System.out.println("找不到要删除的节点");
            }
        }
        //根据头节点计算node数量
        public int length(HeroNode head){
            if (head == null){
                return 0;
            }
            HeroNode tmp = head;
            int size = 0;
            while (true){
                if (tmp.next != null){
                    size ++;
                    tmp = tmp.next;
                }else{
                    break;
                }
            }
            return size;
        }
        //TODO 返回倒数的第k个节点
        public HeroNode findReciprocalNode(int k){
            int length = this.length(head);

            int needIndex = length - k;
            if (needIndex <0){
                return null;
            }

            HeroNode tmp = head;
            int i = 0; //当前遍历的index
            while (true){
                if (i>needIndex){
                    break;
                }
                tmp = tmp.next;
                i++;
            }
            return tmp;
        }
        //TODO 翻转单链表
        public void reverseLinkList(){
            //如果为空或者只有1个元素
            if (head.next == null || head.next.next==null){
                return;
            }

            HeroNode cur = head.next;
            HeroNode next =null;
            HeroNode reverseNode = new HeroNode(0,"",null);
            while (cur != null){
                next = cur.next; //保存当前节点
                cur.next = reverseNode.next; // cur 的下个节点指向新链表的最前端
                reverseNode.next = cur; //cur 连到新链表
                cur = next; //cur后移
            }
            head.next = reverseNode.next;
        }

        //TODO 倒着输出单链表
        public void reversePrint(){
            Stack<HeroNode> stack = new Stack<HeroNode>();
            HeroNode tmp = head.next;
            while (tmp != null){
                stack.add(tmp);
                tmp = tmp.next;
            }
            while (!stack.isEmpty()){
                System.out.println(stack.pop());
            }

        }
    }

    public static void main(String[] args) {
        SingleLinkList singleLinkList = new SingleLinkList();
        singleLinkList.add(new HeroNode(1,"nsld",null));
        singleLinkList.add(new HeroNode(2,"nsld2",null));
        singleLinkList.add(new HeroNode(23,"nsld23",null));
//        singleLinkList.list();
        singleLinkList.addByOrder(new HeroNode(4,"nsld",null));
        singleLinkList.addByOrder(new HeroNode(2,"nsld2",null));
        singleLinkList.addByOrder(new HeroNode(3,"nsld23",null));
        singleLinkList.list();
//        System.out.println(singleLinkList.length(singleLinkList.head));

//        findDaoshu(singleLinkList);

//        xiugai(singleLinkList);

//        shanchu(singleLinkList);

//        fanzhuan(singleLinkList);

//        singleLinkList.reversePrint();
    }

    private static void fanzhuan(SingleLinkList singleLinkList) {
        System.out.println("--------reverse---------");
        singleLinkList.reverseLinkList();
        singleLinkList.list();
        System.out.println("--------reverse---------");
    }

    private static void shanchu(SingleLinkList singleLinkList) {
        System.out.println("--------remove---------");
        singleLinkList.remove(121);
        singleLinkList.remove(1);
        singleLinkList.list();
        System.out.println("--------remove---------");
    }

    private static void xiugai(SingleLinkList singleLinkList) {
        System.out.println("--------update---------");
        singleLinkList.update(new HeroNode(1,"nsld1231",null));
        singleLinkList.list();
        System.out.println("--------update---------");
    }

    private static void findDaoshu(SingleLinkList singleLinkList) {
        System.out.println(singleLinkList.findReciprocalNode(5));
    }
}
