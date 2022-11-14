package com.abc;

import java.util.Scanner;

public class Work03 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Manager manager = new Manager();
        System.out.print("请输入经理的姓名:");
        manager.setName(scanner.nextLine());
        System.out.print("请输入经理的工号:");
        manager.setId(Integer.valueOf(scanner.nextLine()));
        System.out.print("请输入经理的工资:");
        manager.setSalary(Double.valueOf(scanner.nextLine()));
        System.out.print("请输入经理的奖金:");
        manager.setBonus(Double.valueOf(scanner.nextLine()));

        Coder coder = new Coder();
        System.out.print("请输入程序员的姓名:");
        coder.setName(scanner.nextLine());
        System.out.print("请输入程序员的工号:");
        coder.setId(Integer.valueOf(scanner.nextLine()));
        System.out.print("请输入程序员的工资:");
        coder.setSalary(Double.valueOf(scanner.nextLine()));

        manager.work();
        coder.work();
    }
}
