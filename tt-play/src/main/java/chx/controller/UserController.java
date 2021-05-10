package chx.controller;

import chx.domain.User;
import chx.service.UserService;

import java.util.List;
import java.util.Scanner;

public class UserController {

    private static UserService userService = new UserService();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line;
        while (true){
            System.out.println("请输入");
            line = in.nextLine();
            switch (line){
                case "1":{
                    System.out.println("查询所有");
                    List<User> all = userService.findAll();
                    System.out.println(all);
                    break;
                } case "2":{
                    System.out.println("条件查询请输入条件");
                    line = in.nextLine();
                    List<User> users = userService.find(line);
                    System.out.println(users);
                    break;
                } default:{
                    System.out.println("输入错误");
                }
            }
        }
    }
}
