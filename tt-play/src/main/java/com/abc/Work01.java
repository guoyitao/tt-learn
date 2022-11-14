package com.abc;

import java.util.Scanner;

public class Work01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入姓名:");
        String name = scanner.nextLine();
        System.out.print("请输入性别:");
        String sex = scanner.nextLine();
        System.out.print("请输入年龄:");
        Integer age = Integer.valueOf(scanner.nextLine());
        System.out.print("请输入地址:");
        String address = scanner.nextLine();
        System.out.print("请输入饭卡余额:");
        Double money = Double.valueOf(scanner.nextLine());

        System.out.printf("" +
                "************************\n" +
                "我的姓名是：%s\n" +
                "我的性别是：%s\n" +
                "我的年龄是：%d\n" +
                "我的地址是：%s\n" +
                "我的饭卡余额是：%.2f元\n" +
                "************************",name,sex,age,address, money);
    }
}
