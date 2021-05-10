package com.example.demo.service.topic;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service()
public class TopicOrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void mkOrder(String userId,String productId,int num){
        String orderId = UUID.randomUUID().toString();
        rabbitTemplate.convertAndSend("topic_order_exchange","com.email.sms.taotao",orderId);
    }

}
