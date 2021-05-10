package com.tt.service;

import com.rabbitmq.client.Channel;
import com.tt.pojo.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class OrderDispatcherService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    AtomicInteger atomicInteger = new AtomicInteger(0);


    /**
     * 控制死循环方案
     * 1、控制重发次数+ 死信
     * 2、try catch 手动ack
     * 3、try catch 手动ack +死信队列 + 人工干预
     */
    @RabbitListener(queues = {"order_queue"})
    public void orderListener(String order, Channel channel, CorrelationData correlationData, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
//        System.out.printf("收到的是%s %d",order,atomicInteger.getAndIncrement());
//        Order orderObj = JsonUtils.readValue(order, Order.class);
//        System.out.println(0/1 +1/0); //因为是自动ack，然后消息一直消费失败导致  死循环
//        insertOrder(orderObj.getOrderId());

//        2、try catch 手动ack
        try {
            System.out.printf("收到的是%s %d", order, atomicInteger.getAndIncrement());
            Order orderObj = JsonUtils.readValue(order, Order.class);
            System.out.println(0 / 1 + 1 / 0); //因为是自动ack，然后消息一直消费失败导致  死循环
            insertOrder(orderObj.getOrderId());
            channel.basicAck(tag, false);
        } catch (Exception e) {
            try {
                //拒绝不重发，消息扔掉（丢失），配置好的重试次数无效
                //如果配了死信队列，就会进入死信队列
                channel.basicNack(tag, false, false);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }


    @RabbitListener(queues = {"dead_order_fanout_queue"})
    public void deadOrderListener(String order, Channel channel, CorrelationData correlationData, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
//        System.out.printf("收到的是%s %d",order,atomicInteger.getAndIncrement());
//        Order orderObj = JsonUtils.readValue(order, Order.class);
//        System.out.println(0/1 +1/0); //因为是自动ack，然后消息一直消费失败导致  死循环
//        insertOrder(orderObj.getOrderId());

//        2、try catch 手动ack
        try {
            System.out.printf("deadOrderListener收到的是%s %d", order, atomicInteger.getAndIncrement());
            //todo 幂等性，数据库设置主键ok ，分布式锁

            Order orderObj = JsonUtils.readValue(order, Order.class);
            System.out.println(0 / 1 + 1 / 0); //因为是自动ack，然后消息一直消费失败导致  死循环
            insertOrder(orderObj.getOrderId());
            channel.basicAck(tag, false);
        } catch (Exception e) {
            try {
                System.out.println("人工干预订单，消息转移到其他地方");

                channel.basicNack(tag, false, false);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }


    public void insertOrder(String orderId) {
        if ("10086".equals(orderId)) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String sql = "INSERT INTO orderdispatcher VALUES (?, ?, ?, ?,?)";
        int num = jdbcTemplate.update(sql, orderId, UUID.randomUUID().toString(), "汉堡王", new Date(), 0);
        if (num != 1) {
            throw new RuntimeException("创建派送订单失败");
        }
    }
}
