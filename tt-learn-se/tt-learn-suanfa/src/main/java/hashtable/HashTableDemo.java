package hashtable;

/**
 * description: 哈希表
 * author: Guo Yitao
 * create: 2020-07-29 12:43
 **/
public class HashTableDemo {

	public static void main(String[] args) {
		HashTabb hashTabb = new HashTabb(10);
		hashTabb.add(new Emp(1,"asda"));
		hashTabb.add(new Emp(1,"a1111111sda"));
		hashTabb.add(new Emp(2,"asda"));

		hashTabb.list();

		System.out.println(hashTabb.find(99));
	}
}

class HashTabb {
	private EmpLinkedList[] empLinkedLists;
	private int size; //表示有多少链表

	public HashTabb(int size) {
		this.empLinkedLists = new EmpLinkedList[size];
		this.size = size;

		for (int i = 0; i < size; i++) {
			empLinkedLists[i] = new EmpLinkedList();
		}
	}

	//添加
	public void add(Emp emp){
		int empLinkedListNO = hashFun(emp.id);
		empLinkedLists[empLinkedListNO].add(emp);
	}

	public void list(){
		for (int i = 0; i < size; i++) {
			empLinkedLists[i].list(i);
		}
	}

	public Emp find(int id){
		for (EmpLinkedList empLinkedList : empLinkedLists) {
			Emp byId = empLinkedList.findById(id);
			if (byId != null){
				return byId;
			}
		}
		return null;
	}

	public int hashFun(int id){
		return id % size;
	}
}

class Emp {
	public int id;
	public String name;

	public Emp next;

	public Emp(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Emp{" + "id=" + id + ", name='" + name + '\'' + ", next=" + next + '}';
	}
}

class EmpLinkedList{
	//head指针,指向第一个emp
	private Emp head = null;


	public void add(Emp emp){
		//是不是第一个
		if (head ==null) {
			head = emp;
			return;
		}

		//挂到链表最后
		Emp curEmp = head;
		while (true){
			if (curEmp.next ==null){
				break;
			}
			curEmp = curEmp.next;
		}
		curEmp.next = emp;
	}

	public void list(int no){
		if (head ==null){
			System.out.println(no +  "  链表为空");
			return;
		}

		Emp curEmp = head;
		while (true){
			System.out.printf("第%d条链表为 id=%d name=%s   ",no, curEmp.id,curEmp.name);
			if (curEmp.next ==null){
				break;
			}
			curEmp = curEmp.next;
		}
		System.out.println();
	}

	public Emp findById(int id){
		if (head==null){
			return null;
		}
		Emp curEmp = head;
		while (true){
			if (curEmp.id == id){
				break;
			}

			if (curEmp.next ==null){
				curEmp = null;
				break;
			}
			curEmp = curEmp.next;
		}
		return curEmp;
	}


}
