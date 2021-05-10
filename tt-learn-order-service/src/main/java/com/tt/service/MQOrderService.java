package com.tt.service;

import com.tt.pojo.Order;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

/**
 * 可靠生产，冗余存储发送到mq的数据，利用消息投递确认机制的回调，把状态值从status=0 -> status=1
 */
@Service
@EnableScheduling
public class MQOrderService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    RabbitTemplate rabbitTemplate;

//把投递失败的订单status=0的订单查出来，重新投递
//    @Scheduled(cron = "")
//    public void reTry(){
//
//    }

    @PostConstruct
    public void initialize(){
        //消息确认
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("cause:"+cause);
                String id = correlationData.getId();
                if (!ack){
                    System.out.println("应答失败：" + id);
                    return;
                }
                String sql = "update order_message set status=1 where orderId =?";

                int update = 0;
                try {
                    update = jdbcTemplate.update(sql, id);
                    if (1 == update) {
                        System.out.println("消息状态修改成功,消息成功投递");
                    }
                } catch (Exception e) {
                    System.out.println("消息状态修改失败");
                }
            }
        });
    }


    public void insertOrder(Order order) {
        addOrder(order);

        addOrderMessage(order);

//        dispatcherOrder(order.getOrderId());

        //异步投递
        convertAndSend(order);
    }

    private void addOrderMessage(Order order) {
        String sql = "insert into order_message VALUES(?,?,?,?,?)";
        int update = jdbcTemplate.update(sql, order.getOrderId(), order.getUserId(), order.getOrderContent(), order.getCreateTime(),0);
        if (update != 1) {
            throw new RuntimeException("本地消息保存失败");
        }
    }

    private void addOrder(Order order) {
        String sql = "insert into orderaa VALUES(?,?,?,?)";
        int update = jdbcTemplate.update(sql, order.getOrderId(), order.getUserId(), order.getOrderContent(), order.getCreateTime());
        if (update != 1) {
            throw new RuntimeException("订单插入失败");
        }
    }

    public String dispatcherOrder(String orderId) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(2000);
        factory.setConnectTimeout(3000);

        RestTemplate restTemplate = new RestTemplate(factory);
        return restTemplate.getForObject("http://localhost:8082/dispatcher?orderId=" + orderId, String.class);

    }

    private void convertAndSend(Order order){

        rabbitTemplate.convertAndSend("order_fanout_exchange","", JsonUtils.writeValueAsString(order),new CorrelationData(order.getOrderId()));
    }
}
