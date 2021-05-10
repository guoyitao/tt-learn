package com.gyt.shiyan3;

import java.util.Scanner;

public class Exp {
    private static final String reg = "[\\u4e00-\\u9fa5]+";
    private static final String STU_ID = "203233Y215";

    public static void main(String [] args){
        Scanner input = new Scanner(System.in);
        Wait wt = new Wait();
        while (true){
            try {
                System.out.print("请输入姓名：");
                String name = input.next();
                if (STU_ID.equals(name)){
                    System.out.println("学号：" + name);
                    break;
                }
                if (!name.matches(reg)){
                    System.out.println("请输入中文姓名！");
                    continue;
                }
                wt.setName(name);

                System.out.print("请输入年龄：");

                int age = input.nextInt();
                if (age<=0){
                    System.out.println("年龄必须大于0 ！");
                    continue;
                }

                wt.setAge(age);
                wt.show();
            } catch (Exception e) {
                continue;
            }
        }


    }
}
