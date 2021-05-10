package com.example.demo.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setUsername("guest");
        connectionFactory.setVirtualHost("/");
        String queueName = "simple_queue_test";

        Connection conn = null;
        Channel channel = null;

        try {
            conn = connectionFactory.newConnection("生产者");
            channel = conn.createChannel();
            channel.queueDeclare(queueName,false,false,false,null );
            //发送
            channel.basicPublish("",queueName,null,"hello simple 简单模式".getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭
            if (channel != null && channel.isOpen()) {
                try {
                    channel.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null && conn.isOpen()) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
