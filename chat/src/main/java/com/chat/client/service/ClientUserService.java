package com.chat.client.service;

import com.chat.client.Client;
import com.chat.client.view.SystemView;
import com.chat.domain.Message;
import com.chat.domain.MessageCallback;
import com.chat.domain.MessageType;
import com.chat.domain.User;
import com.chat.util.JsonUtil;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Pattern;

/**
 * @author: Luck_chen
 * @date: 2022/11/7 22:01
 * @Version 1.0.0.0
 */
public class ClientUserService {
    SystemView systemView = SystemView.getInstance();

    public  static  User userLogined = null; //当前登录的用户

    //注册
    public boolean register() {
        User user = new User();
        Scanner sc = new Scanner(System.in);
        /**
         * 验证用户名
         * while循环控制直到输入合法为止
         */
        System.out.println("请输入用户名(用户名由数字、字母、下划线任意组合,长度为3~10)：");
        String uname = sc.next();
        while (!checkName(uname)) {
            System.out.println("用户名不合法，请重新输入：");
            uname = sc.next();
        }
        user.setUsername(uname);
        /**
         * 验证密码
         * while循环控制直到输入合法为止
         */
        System.out.println("请输入密码(密码由数字、字母、下划线任意组合,长度为6~20)：");
        String upassword = sc.next();
        while (!checkPwd(upassword)) {
            System.out.println("密码不合法，请重新输入：");
            upassword = sc.next();
        }
        user.setPassword(upassword);

        /**
         * 验证邮箱
         * while循环控制直到输入合法为止
         */
        System.out.println("请输入邮箱：");
        String uemail = sc.next();
        while (!checkEmail(uemail)) {
            System.out.println("邮箱不合法，请重新输入：");
            uemail = sc.next();
        }
        user.setEmail(uemail);
        //添加到数据库
//        userList.add(user);
        //创建消息对象,存储的注册的用户信息
        Message message = new Message();
        //设置消息类型
        message.setMessageType(MessageType.Register);
        //封装消息格式
        message.setData(JsonUtil.writeObjectAsString(user));
        //创建锁对象
        CountDownLatch latch = new CountDownLatch(1);
        //把注册信息发给服务端，等待服务端回馈注册是否成功
        try {
            Client.send(message, new MessageCallback() {
                @Override
                public void callback(Message message) {
                    if (message.getState()) {
                        System.out.println("注册成功！");
                    } else {
                        System.out.println(message.getData());
                    }
                    //打开主线程的锁
                    latch.countDown();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            //等待send方法结束
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    //检查邮箱
    private boolean checkEmail(String uemail) {
        if ((uemail != null) && (!uemail.isEmpty())) {
            return Pattern.matches("^(\\w+([-.][A-Za-z0-9]+)*){3,18}@\\w+([-.][A-Za-z0-9]+)*\\.\\w+([-.][A-Za-z0-9]+)*$", uemail);
        }
        return false;
    }

    //检查密码
    private boolean checkPwd(String upassword) {
        String regExp = "^[\\w_]{6,20}$";
        if (upassword.matches(regExp)) {
            return true;
        }
        return false;
    }

    //检查用户名
    private boolean checkName(String uname) {
        String regExp = "^[\\u4e00-\\u9fa5a-zA-Z0-9_]{2,20}$";
        if (uname.matches(regExp)) {
            return true;
        } else {
            return false;
        }
    }

    //找回密码
    public void findPassword() {
        //输入用户名和邮箱
        User user = new User();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        user.setUsername(sc.next());
        System.out.println("请输入邮箱：");
        user.setEmail(sc.next());
        //把用户名和邮箱传进服务端进行判断
        Message message = new Message();
        message.setMessageType(MessageType.FindPassword);
        message.setData(JsonUtil.writeObjectAsString(user));
        CountDownLatch latch = new CountDownLatch(1);
        try {
            Client.send(message, new MessageCallback() {
                @Override
                public void callback(Message message) {
                    //用户名和密码都正确就提示用户登录成功，并进入系统首页
                    if (message.getState()) {
                        User responseUser = JsonUtil.readToObject(message.getData(), User.class);
                        System.out.printf("找回密码成功 账号%s 的密码是%s\n", responseUser.getUsername(), responseUser.getPassword());
                    } else {
                        System.out.println(message.getData());
                    }
                    //释放锁
                    latch.countDown();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    //登录
    public void login() {
        User user = new User();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        user.setUsername(sc.next());
        System.out.println("请输入密码：");
        user.setPassword(sc.next());
        //把user传到服务端进行判断
        Message message = new Message();
        message.setMessageType(MessageType.Login);
        message.setData(JsonUtil.writeObjectAsString(user));
        CountDownLatch latch = new CountDownLatch(1);
        try {
            Client.send(message, new MessageCallback() {
                @Override
                public void callback(Message message) {
                    //用户名和密码都正确就提示用户登录成功，并进入系统首页
                    if (message.getState()) {
                        System.out.println("登录成功！欢迎" + user.getUsername());
                        Client.initChat();
                        //记录当前登录的用户状态
                        userLogined = JsonUtil.readToObject(message.getData(), User.class);
                    } else {
                        System.out.println(message.getData());
                    }
                    //释放锁
                    latch.countDown();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //等待服务器响应
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (userLogined == null) {
            return;
        }
        systemView.inputChoice();
    }

    public void loginOut() {
        Message message = new Message();
        message.setMessageType(MessageType.LoginOut);
        message.setData(userLogined != null ? userLogined.getUsername() : null);
        CountDownLatch latch = new CountDownLatch(1);
        try {
            Client.send(message, new MessageCallback() {
                @Override
                public void callback(Message message) {
                    latch.countDown();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //等待服务器响应
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
