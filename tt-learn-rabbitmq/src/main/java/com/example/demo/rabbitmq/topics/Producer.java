package com.example.demo.rabbitmq.topics;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class Producer {
    public static void main(String[] args) {
        for (int i = 0;i<3; i++) {
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
            String exchange = "topic_ex";
            String exType = "topic";
            String queue = "queue";

//            String routingKey = "com.order.test.x";
            String routingKey = "com.123.user.123";
            channel.basicPublish(exchange, routingKey,null,msg.getBytes(StandardCharsets.UTF_8));
            System.out.println("success");

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
