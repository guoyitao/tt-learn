package com.example.demo.rabbitmq.fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class Producer {
    public static void main(String[] args) {
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
            String exchange = "fanout_exchange";
            String queue = "queue";
            String message = "hello fanout_exchange";
            String routingKey ="";

            channel.basicPublish(exchange,routingKey,null,message.getBytes(StandardCharsets.UTF_8));
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
