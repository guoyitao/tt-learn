package com.example.demo.rabbitmq.all;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Coutsumer {

    static ConnectionFactory connectionFactory = new ConnectionFactory();

    static {
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setUsername("guest");
        connectionFactory.setVirtualHost("/");
    }

    public static void main(String[] args) throws IOException {
        new Thread(new Job("queue1")).start();
        new Thread(new Job("queue2")).start();
        new Thread(new Job("queue3")).start();
        new Thread(new Job("queue4")).start();
        new Thread(new Job("queue5")).start();
        System.in.read();
    }

    static class Job implements Runnable{

        String queue;

        public Job(String queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            Connection conn = null;
            Channel channel = null;
            try {

                conn = connectionFactory.newConnection("消费者");
                channel = conn.createChannel();

                //queue已经创建不需要定义
//            channel.queueDeclare(queue,false,false,false,null);

                channel.basicConsume(queue, true, new DeliverCallback() {
                    @Override
                    public void handle(String consumerTag, Delivery message) throws IOException {
                        System.out.println(message.getEnvelope().getDeliveryTag());
                        System.out.println(queue +  "收到的是" + new String(message.getBody(), StandardCharsets.UTF_8));
                    }
                }, new CancelCallback() {
                    @Override
                    public void handle(String consumerTag) throws IOException {
                        System.out.println("接受失败了！。。。" + consumerTag);
                    }
                });
                System.in.read();
                System.out.println(queue+"  start");
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
}
