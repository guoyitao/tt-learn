package com.chat.client.view;

import com.chat.client.Client;
import com.chat.client.service.ClientUserService;

import java.util.Scanner;

/**
 * @description:首页界面
 * @author: Luck_chen
 * @date: 2022/11/7 21:50
 * @Version 1.0.0.0
 */

public class Index {
    private static final Index instance = new Index();

    private Index(){

    }

    public static Index getInstance() {
        return instance;
    }

    //显示界面
    private void show() {
        System.out.println("1.注册\t2.登录\t3.找回密码\t4.退出");
    }

    public void inputChoice(){
        //创建UserService对象
        ClientUserService clientUserService = new ClientUserService();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            show();
            //输入选择
            System.out.println("请输入要选择的功能数字代号:");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 :
                    //调用注册方法
                    clientUserService.register();
                    break;
                case 2 :
                    //调用登录方法
                    clientUserService.login();
                    break;
                case 3:
                    //调用找回密码方法
                    clientUserService.findPassword();
                    break;
                case 4 :
                    //退出
                    clientUserService.loginOut();
                    Client.closeSocket();
                    System.exit(0);
                default:
                    System.out.println("没有这个选择，请重新输入选择：");
            }
        }
    }
}
