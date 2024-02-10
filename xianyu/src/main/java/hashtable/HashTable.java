package hashtable;

//创建hashtable管理多条链表
class HashTable {
    private EmpLinkedList[] empLinkedLists;
    private int size;//表示共有多少条链表

    //空参构造器，数组默认初始化为5
    public HashTable() {
        this.size = 5;
        empLinkedLists = new EmpLinkedList[size];
        //分别初始化每一个链表
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }
    //带参构造器
    public HashTable(int size) {
        //初始化
        this.size = size;
        empLinkedLists = new EmpLinkedList[size];
        //分别初始化每一个链表
        for (int i = 0; i < size; i++) {
            empLinkedLists[i] = new EmpLinkedList();
        }
    }

    //添加员工
    public void add(Emp emp) {
        //根据员工id，得到该员工要插入到哪条链表
        int empLinkedListNum = hashFun(emp.id);
        //将emp添加到对应的链表中
        empLinkedLists[empLinkedListNum].add(emp);

    }

    //遍历所有链表，遍历哈希表
    public void list() {
        for (int i = 0; i < size; i++) {
            empLinkedLists[i].list(i);
        }
    }

    //根据id查找员工
    public void findEmpById(int id) {
        //使用散列函数确定到哪条链表中查找
        int empLinkedListNum = hashFun(id);
        Emp emp = empLinkedLists[empLinkedListNum].findEmpById(id);
        if (emp != null) {//找到了
            System.out.printf("在第%d条链表中找到员工 id=%d\n",(empLinkedListNum+1),id);
        } else {
            System.out.println("未找到该员工");
        }
    }

    //编写一个简单的散列函数
    public int hashFun(int id) {
        return id % size;
    }
}
