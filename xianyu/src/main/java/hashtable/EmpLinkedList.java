package hashtable;

//创建EmpLinkedList,表示链表
class EmpLinkedList {
    private Emp head;//头指针，默认为空

    //添加员工到链表,假设id是自增长的
    public void add(Emp emp) {
        if (head == null) {//如果是第一个员工
            head = emp;
            return;
        }
        //如果不是第一个员工
        Emp temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = emp;
    }

    //遍历链表
    public void list(int no) {
        if (head == null) {//链表为空
            System.out.println("第" + (no + 1) + "条链表为空！");
            return;
        }
        System.out.print("第" + (no + 1) + "条链表的信息为：");
        Emp temp = head;
        while (true) {
            System.out.printf("=> id=%d name=%s\t", temp.id, temp.name);
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        System.out.println();
    }

    //根据id查找员工
    public Emp findEmpById(int id) {
        if (head == null) {
            System.out.println("链表空");
            return null;
        }
        Emp temp = head;
        while (true) {
            if (temp.id == id) {//找到员工
                break;
            }
            if (temp.next == null) {//没有找到员工
                temp = null;
                break;
            }
            temp = temp.next;
        }
        return temp;
    }
}
