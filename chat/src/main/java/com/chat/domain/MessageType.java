package com.chat.domain;

/**
 * @description://事件
 * @author: Luck_chen
 * @date: 2022/11/8 20:17
 * @Version 1.0.0.0
 */
public enum MessageType {
    //1.注册
    Register,
    //2.登录
    Login,
    LoginOut,
    //3.找回密码
    FindPassword,
    //1.查看在线人员名单
    OnlinePeople,
    // 2.私聊
    PrivateChat,
    PrivateChatShowMsg,
    // 3.群聊
    GroupChat,
    GroupChatShowMsg,
    Cancellation,
    // 6.修改密码
    UpdatePassword, Exit;

}
