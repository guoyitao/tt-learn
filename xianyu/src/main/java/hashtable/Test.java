package hashtable;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        //创建一个哈希表
        HashTable hashTable = new HashTable(5);

        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:添加员工");
            System.out.println("list:显示员工");
            System.out.println("find:查找员工");
            System.out.println("exit:退出系统");

            key = scanner.next();

            switch (key) {
                case "add":
                    System.out.print("请输入员工id：");
                    int id = scanner.nextInt();
                    System.out.print("请输入员工name：");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTable.add(emp);
                    break;
                case "list":
                    System.out.println("员工信息如下：");
                    hashTable.list();
                    break;
                case "find":
                    System.out.print("请输入要查找的员工id：");
                    id = scanner.nextInt();
                    hashTable.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;

            }
        }
    }

}
