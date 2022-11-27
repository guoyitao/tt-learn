package com.chat.server.service;

import com.chat.domain.Message;
import com.chat.domain.MessageType;

import java.net.Socket;

/**
 * @description://判断客户端的请求，对相应的请求进行处理
 * @author: Luck_chen
 * @date: 2022/11/8 21:33
 * @Version 1.0.0.0
 */
public class CommonService {

    private ServerUserService serverUserService = new ServerUserService();
    private ChatService chatService = new ChatService();
    public Message handler(Message message, Socket socket) {
        if (message.getMessageType() == MessageType.Login){
            //登录逻辑
            return serverUserService.login(message,socket);
        }else if (message.getMessageType() == MessageType.Register){
            //注册
            return serverUserService.register(message,socket);
        }else if (message.getMessageType() == MessageType.FindPassword){
            //找回密码
            return serverUserService.findPassword(message,socket);
        }else if (message.getMessageType() == MessageType.LoginOut){
            //找回密码
            return serverUserService.loginOut(message,socket);
        }else if (message.getMessageType() == MessageType.OnlinePeople){
            //在线列表
            return chatService.onlinePeople(message,socket);
        }else if (message.getMessageType() == MessageType.PrivateChat){
            //私聊
            return chatService.privateChat(message,socket);
        }else if (message.getMessageType() == MessageType.GroupChat){
            //群聊
            return chatService.groupChat(message,socket);
        }else if (message.getMessageType() == MessageType.Cancellation){
            //注销账号
            return chatService.cancellation(message,socket);
        }else if (message.getMessageType() == MessageType.UpdatePassword){
            //注销账号
            return chatService.updatePassword(message,socket);
        }

        return null;
    }
}
