package com.chat.domain;

/**
 * @description:消息类 规定交互的协议
 * @author: Luck_chen
 * @date: 2022/11/8 20:16
 * @Version 1.0.0.0
 */
public class Message {
    private MessageType messageType; //消息名称
    private String data; //消息体
    private Boolean state;  //请求是否成功

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
