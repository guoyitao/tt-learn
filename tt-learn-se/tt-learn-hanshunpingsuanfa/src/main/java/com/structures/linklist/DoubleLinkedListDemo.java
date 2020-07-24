package com.structures.linklist;

/**
 * description: 双向链表
 * author: Guo Yitao
 * create: 2020-07-24 14:36
 **/
public class DoubleLinkedListDemo {
    private static class HeroNode{
        int no;
        HeroNode pre;
        HeroNode next;
        String data;

        public HeroNode(int no,  String data) {
            this.no = no;
            this.data = data;
        }

        @Override
        public String toString() {
            return "HeroNode{" + "no=" + no + ", data='" + data + '\'' + '}';
        }
    }

    private static class DoubleLinkedList{
        private static HeroNode head = new HeroNode(0,"head");

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

        public void add(HeroNode node){
            HeroNode tmp = head;
            //遍历找到最后一个节点
            while (true){
                if (tmp.next==null) {
                    break;
                }
                tmp = tmp.next;
            }
            tmp.next = node;
            node.pre = tmp;
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
                tmp.data = heroNode.data;
            }else {
                System.out.println("Node not found！");
            }

        }

        public void remove(int no){
            if (head.next == null){
                System.out.println("链表为空 无法删除");
                return;
            }

            HeroNode tmp = head.next;
            boolean flag = false;
            while (true){
                //已经遍历到最后
                if (tmp == null) {
                    break;
                }
                if (tmp.no == no){
                    //找到了要删除的
                    flag = true;
                    break;
                }
                tmp = tmp.next;
            }
            if (flag){
                tmp.pre.next = tmp.next;
                //删最一个元素的情况
                if (tmp.next!=null){
                    tmp.next.pre = tmp.pre;
                }
            }else {
                System.out.println("找不到要删除的节点");
            }
        }

        //顺序插入
        public void addByOrder(HeroNode heroNode){
            HeroNode tmp = this.head.next;
            boolean flag = false; //是否加入
            while (true){
                //找到了，已经遍历到最后
                if (tmp == null) {
                    break;
                }
                if (tmp.no > heroNode.no ){
                    //找到了，当前加入的元素比后面小
                    break;
                }else if (tmp.no == heroNode.no){
                    flag = true;
                    break;
                }
                tmp = tmp.next;
            }
            if (flag){
                System.out.println( heroNode+"  的编号已经存在" );
            }else {
                tmp.pre.next = heroNode;
                heroNode.pre = tmp.pre;
                tmp.pre = heroNode;
                heroNode.next = tmp;

            }
        }

    }

    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(new HeroNode(1,"12"));
        doubleLinkedList.add(new HeroNode(2,"1asdad"));
        doubleLinkedList.add(new HeroNode(5,"1阿萨德2"));
        doubleLinkedList.addByOrder(new HeroNode(4,"啊啊啊啊啊啊啊啊啊"));
//        doubleLinkedList.remove(1);

//        doubleLinkedList.update(new HeroNode(1,"按时大多数"));



        doubleLinkedList.list();
    }

}
