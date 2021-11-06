package homework1;

import java.util.Scanner;

public class TestBook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String title;
        Integer pageNum;
        String type;

        System.out.println("请输入标题");
        title = scanner.next();
        System.out.println("请输入页数");
        pageNum = scanner.nextInt();
        System.out.println("请输入种类");
        type = scanner.next();

        Book book = new Book(title, pageNum, type);
        book.detail();


        System.out.println("book2");
        System.out.println("请输入页数");
        pageNum = scanner.nextInt();
        System.out.println("请输入种类");
        type = scanner.next();

        Book book2 = new Book( pageNum, type);
        book2.detail();
    }
}
