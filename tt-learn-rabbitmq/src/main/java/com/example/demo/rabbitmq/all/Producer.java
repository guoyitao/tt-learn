package com.example.demo.rabbitmq.all;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class Producer {
    public static void main(String[] args) {
        for (int i = 0;; i++) {
            send(String.valueOf(i));
        }
    }

    private static void send(String msg) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setUsername("guest");
        connectionFactory.setVirtualHost("/");
        Connection conn = null;
        Channel channel = null;
        try {
            conn = connectionFactory.newConnection("生产者");
            channel = conn.createChannel();

            //群发，目前已经在web界面绑定好queue
            String exchange = "direct_order";
            String exType = "direct";
            channel.exchangeDeclare(exchange,exType,true);//创建交换机
            channel.queueDeclare("queue4",true,false,false,null); //创建队列
            channel.queueDeclare("queue5",true,false,false,null); //创建队列
            channel.queueBind("queue3",exchange,"order");
            channel.queueBind("queue4",exchange,"user");
            channel.queueBind("queue5",exchange,"user");
            for (int i = 0; i < 10000; i++) {
                channel.basicPublish(exchange,"user",null,msg.getBytes(StandardCharsets.UTF_8));
            }


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
