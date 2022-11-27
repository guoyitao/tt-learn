package com.chat.server.service;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import com.chat.domain.Message;
import com.chat.domain.MessageType;
import com.chat.domain.Session;
import com.chat.domain.User;
import com.chat.server.dao.UserDao;
import com.chat.util.JsonUtil;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 用户业务
 * @author: Luck_chen
 * @date: 2022/11/8 21:36
 * @Version 1.0.0.0
 */
public class ServerUserService {
    //记录登录的用户
    public final static ConcurrentHashMap<String, Session> sessonMap = new ConcurrentHashMap<>();

    private UserDao userDao = new UserDao();


    public static TimedCache<String, Integer> timedCache = CacheUtil.newTimedCache(TimeUnit.MINUTES.toMillis(5));

    public static Session getSession(String username){
        return sessonMap.get(username);
    }
    public static void  setSession(String username, Socket socket){
        sessonMap.put(username,new Session(username,socket));
    }

    public Message login(Message message, Socket socket) {
        //把客户端写的user解析出来给服务器去数据库查询是否存在该用户
        User loginUser = JsonUtil.readToObject(message.getData(), User.class);
        //根据用户名查询用户
        User user = userDao.selectUser(loginUser.getUsername());
        //响应信息
        Message response = new Message();
        if (user != null && user.getPassword().equals(loginUser.getPassword())){
            //登录成功
            //设置响应信息
            response.setData(JsonUtil.writeObjectAsString(user));
            response.setMessageType(message.getMessageType());
            //设置响应状态
            response.setState(true);
            Session session = getSession(user.getUsername());
            if (session != null){
                try {
                    Message exit = new Message();
                    exit.setMessageType(MessageType.Exit);
                    if (socket != session.getSocket() ){
                        session.send(JsonUtil.writeObjectAsString(exit));
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println(user.getUsername() + " 登录了");

            //记录客户端是哪个用户使用
            setSession(user.getUsername(), socket);
        }else{
            //登录失败
            Integer errorTime = timedCache.get(loginUser.getUsername());
            if (errorTime == null){
                timedCache.put(loginUser.getUsername(), 1);
            }else{
                if (errorTime >= 3){
                    response.setData("密码输出错误连续超过3次，已被锁定5分钟");
                    response.setMessageType(message.getMessageType());
                    response.setState(false);
                    return response;
                }
                timedCache.put(loginUser.getUsername(),++errorTime);
            }

            response.setData("登陆失败，账号或密码错误,请重新登录，登录失败三次账号将被锁定");
            response.setMessageType(message.getMessageType());
            response.setState(false);

        }
        return response;
    }

    public Message register(Message message, Socket socket) {
        //把客户端写的user解析出来给服务器去数据库查询是否存在该用户
        User loginUser = JsonUtil.readToObject(message.getData(), User.class);
        //响应信息
        Message response = new Message();

        User userDb = userDao.selectUser(loginUser.getUsername());
        if (userDb != null){
            response.setData("该用户名已存在，不能重复注册");
            response.setMessageType(message.getMessageType());
            response.setState(false);
            return response;
        }
        int count = userDao.insert(loginUser);
        if (count > 0){
            response.setData("注册成功");
            response.setMessageType(message.getMessageType());
            response.setState(true);
            return response;
        }else {
            response.setData("注册失败，服务器数据出错");
            response.setMessageType(message.getMessageType());
            response.setState(false);
            return response;
        }
    }

    public Message findPassword(Message message, Socket socket) {
        User loginUser = JsonUtil.readToObject(message.getData(), User.class);
        //响应信息
        Message response = new Message();
        response.setMessageType(message.getMessageType());

        User userDb = userDao.selectUser(loginUser.getUsername());
        if (userDb == null || !userDb.getEmail().equals(loginUser.getEmail())){
            response.setData("账号名或者邮箱错误，无法找回密码");
            response.setMessageType(message.getMessageType());
            response.setState(false);
            return response;
        }

        response.setState(true);
        response.setData(JsonUtil.writeObjectAsString(userDb));
        return response;
    }

    public Message loginOut(Message message, Socket socket) {
        //响应信息
        Message response = new Message();
        response.setMessageType(message.getMessageType());
        if (message.getData() != null && "".equals(message.getData())){
            sessonMap.remove(message.getData());
        }
        System.out.println(message.getData() + " 退出登录了");
        response.setState(true);
        response.setData(message.getData());
        return response;
    }


}
