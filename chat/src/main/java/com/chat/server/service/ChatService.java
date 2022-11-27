package com.chat.server.service;

import com.chat.client.service.ClientUserService;
import com.chat.domain.*;
import com.chat.server.dao.UserDao;
import com.chat.util.JsonUtil;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 聊天业务
 * @author: Luck_chen
 * @date: 2022/11/8 21:36
 * @Version 1.0.0.0
 */
public class ChatService {

    UserDao userDao = new UserDao();
    public Message onlinePeople(Message message, Socket socket) {
        Message resp = new Message();
        resp.setMessageType(message.getMessageType());

        List<String> userNames = new ArrayList<>();
        for (Session value : ServerUserService.sessonMap.values()) {
            userNames.add(value.getUsername());
        }
        resp.setData(JsonUtil.writeObjectAsString(userNames));
        return resp;
    }

//    私聊
    public Message privateChat(Message message, Socket socket) {
        Message resp = new Message();
        resp.setMessageType(message.getMessageType());

        ChatData chatData = JsonUtil.readToObject(message.getData(), ChatData.class);
        Session session = ServerUserService.getSession(chatData.getTo());
        if (session != null){
            try {
                Message msgChat = new Message();
                msgChat.setState(true);
                msgChat.setMessageType(MessageType.PrivateChatShowMsg);
                msgChat.setData(message.getData());
                session.send(JsonUtil.writeObjectAsString(msgChat));
                resp.setState(true);
                resp.setData("发送成功");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else{
            resp.setState(false);
            resp.setData("发送失败");
        }
        return resp;
    }
//     群聊
    public Message groupChat(Message message, Socket socket) {
        Message resp = new Message();
        resp.setMessageType(message.getMessageType());
        try {
            for (Session session : ServerUserService.sessonMap.values()) {
                Message msgChat = new Message();
                msgChat.setState(true);
                msgChat.setMessageType(MessageType.GroupChatShowMsg);
                msgChat.setData(message.getData());
                session.send(JsonUtil.writeObjectAsString(msgChat));
            }
            resp.setState(true);
            resp.setData("发送成功");
        } catch (IOException e) {
            resp.setState(false);
            resp.setData("发送失败");
            throw new RuntimeException(e);
        }
        return resp;
    }

    public Message cancellation(Message message, Socket socket) {
        Message resp = new Message();
        resp.setMessageType(message.getMessageType());
        User user = JsonUtil.readToObject(message.getData(),User.class);
        User userDb = userDao.selectUser(user.getUsername());
        if (!userDb.getPassword().equals(user.getPassword()) || !userDb.getEmail().equals(user.getEmail())){
            resp.setState(false);
            resp.setData("注销失败,密码或者邮箱错误");
            return resp;
        }

        int count = userDao.delByName(user.getUsername());
        if (count > 0){
            resp.setState(true);
            resp.setData("注销成功");
        }else{
            resp.setState(false);
            resp.setData("注销失败");
        }
        return resp;
    }

    public Message updatePassword(Message message, Socket socket) {
        Message resp = new Message();
        resp.setMessageType(message.getMessageType());

        UserUpdatePasswordRequestData req = JsonUtil.readToObject(message.getData(), UserUpdatePasswordRequestData.class);
        User user = userDao.selectUser(req.getUsername());
        if (!user.getPassword().equals(req.getOldPassword())){
            resp.setState(false);
            return resp;
        }
        int count =userDao.updatePassword(req);
        if (count > 0){
            resp.setState(true);
            return resp;
        }else{
            resp.setState(false);
            return resp;
        }
    }
}
