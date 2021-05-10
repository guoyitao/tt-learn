package chx;

import java.util.*;

public class StudentCollection {
    static Collection<Student> list = new ArrayList<>();

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            addStudent();
        }
        Iterator<Student> iterator = list.iterator();
        while (iterator.hasNext()) {
            Student next = iterator.next();
            System.out.println(next);
        }
    }

    public static void addStudent(){
        Scanner scanner = new Scanner(System.in);
        Student student = new Student(scanner.nextLine(),scanner.nextLine(),scanner.nextLine());
        list.add(student);
    }
}
