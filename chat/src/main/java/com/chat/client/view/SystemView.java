package com.chat.client.view;

import com.chat.client.Client;
import com.chat.client.service.ClientUserService;
import com.chat.client.service.SystemService;

import java.util.Scanner;

/**
 * @description://TODO
 * @author: Luck_chen
 * @date: 2022/11/7 21:50
 * @Version 1.0.0.0
 */
public class SystemView {

    private static final SystemView instance = new SystemView();

    private SystemView(){

    }

    public static SystemView getInstance() {
        return instance;
    }

    //创建UserService对象
    SystemService systemService = new SystemService();
    ClientUserService clientUserService  =new ClientUserService();
    //显示界面
    public void show() {
        System.out.println("1.查看在线人员名单\t2.私聊\t3.群聊\t4.退出系统\t5.账号注销\t6.修改密码\t");
    }

    public void inputChoice() {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            show();
            //输入选择
            System.out.println("请输入要选择的功能数字代号:");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    //调用查看在线人员名单方法
                    systemService.onlinePeple();
                    break;
                case 2:
                    //调用私聊方法
                    systemService.privateChat();
                    break;
                case 3:
                    //调用群聊方法
                    systemService.groupChat();
                    break;
                case 4:
                    Client.stopChat();
                    clientUserService.loginOut();
                    //退出系统
                    return;
                case 5:
                    //账号注销
                    systemService.cancellation();
                    if (ClientUserService.userLogined == null) {
                        Client.stopChat();
                        return;
                    }else {
                        break;
                    }
                case 6:
                    //修改密码
                    systemService.updatePassword();
                    break;
                default:
                    System.out.println("没有这个选择，请重新输入选择：");
            }
        }
    }

}
