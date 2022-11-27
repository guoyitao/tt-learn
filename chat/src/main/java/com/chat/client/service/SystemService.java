package com.chat.client.service;

import com.chat.client.Client;
import com.chat.domain.*;
import com.chat.util.JsonUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

/**
 * @author: Luck_chen
 * @date: 2022/11/7 22:27
 * @Version 1.0.0.0
 */
public class SystemService {
    public void onlinePeple() {
        //判断网络
        //传回在线的用户列表输出
        Message message = new Message();
        message.setMessageType(MessageType.OnlinePeople);
        CountDownLatch latch = new CountDownLatch(1);
        try {
            Client.send(message, new MessageCallback() {
                @Override
                public void callback(Message message) {
                    System.out.println("在线人员为：" + message.getData());
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

    public void privateChat() {
        Scanner sc = new Scanner(System.in);
        ChatData chatData = new ChatData();
        chatData.setFrom(ClientUserService.userLogined.getUsername());

        //输入私聊的用户的用户名
        System.out.print("请输入用户的用户名：");
        chatData.setTo(sc.nextLine());
        System.out.print("请输入你要发送的私聊消息：");
        chatData.setData(sc.nextLine());

        Message message = new Message();
        message.setMessageType(MessageType.PrivateChat);
        message.setData(JsonUtil.writeObjectAsString(chatData));
        try {
            Client.send(message, new MessageCallback() {
                @Override
                public void callback(Message message) {
                    System.out.println(message.getData());
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //群聊
    public void groupChat() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你要发送的群发消息：");
        ChatData chatData = new ChatData();
        chatData.setFrom(ClientUserService.userLogined.getUsername());
        chatData.setData(sc.nextLine());
        Message message = new Message();
        message.setMessageType(MessageType.GroupChat);
        message.setData(JsonUtil.writeObjectAsString(chatData));
        try {
            Client.send(message, new MessageCallback() {
                @Override
                public void callback(Message message) {
                    System.out.println(message.getData());
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //注销账号
    public void cancellation() {
        Scanner sc = new Scanner(System.in);
        User user = new User();
        user.setUsername(ClientUserService.userLogined.getUsername());
        System.out.println("请输入你的密码：");
        user.setPassword(sc.nextLine());
        System.out.println("请输入你的邮箱：");
        user.setEmail(sc.nextLine());

        Message message = new Message();
        message.setMessageType(MessageType.Cancellation);
        message.setData(JsonUtil.writeObjectAsString(user));
        CountDownLatch latch = new CountDownLatch(1);
        try {
            Client.send(message, new MessageCallback() {
                @Override
                public void callback(Message message) {
                    //用户名和密码都正确就提示用户登录成功，并进入系统首页
                    if (message.getState()){
                        System.out.println(message.getData());
                        ClientUserService.userLogined = null;
                    }else{
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

    }


    public void updatePassword() {
        Scanner sc = new Scanner(System.in);
        UserUpdatePasswordRequestData requestData = new UserUpdatePasswordRequestData();
        requestData.setUsername(ClientUserService.userLogined.getUsername());
        System.out.println("请输入旧密码:");
        requestData.setOldPassword(sc.nextLine());
        System.out.println("请输入新密码:");
        requestData.setNewPassword(sc.nextLine());
        if (!checkPwd(requestData.getNewPassword())){
            System.out.println("密码输入格式出错");
//            //请重新输入
//            System.out.println("请输入旧密码:");
//            requestData.setOldPassword(sc.nextLine());
//            System.out.println("请输入新密码:");
//            requestData.setNewPassword(sc.nextLine());
        }
        System.out.println("请输入重复新密码:");
        String newPassword = sc.nextLine();
        if (!newPassword.equals(requestData.getNewPassword())){
            System.out.println("两次密码输入不一致，修改失败");
            return;
        }
        requestData.setNewPassword(newPassword);


        Message message = new Message();
        message.setMessageType(MessageType.UpdatePassword);
        message.setData(JsonUtil.writeObjectAsString(requestData));

        CountDownLatch latch = new CountDownLatch(1);
        try {
            Client.send(message, new MessageCallback() {
                @Override
                public void callback(Message message) {
                    if (message.getState()){
                        System.out.println("修改密码成功");
                    }else{
                        System.out.println("旧密码输入错误，修改密码失败");
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
    }

    private boolean checkPwd(String upassword) {
        String regExp = "^[\\w_]{6,20}$";
        return upassword.matches(regExp);
    }
}
