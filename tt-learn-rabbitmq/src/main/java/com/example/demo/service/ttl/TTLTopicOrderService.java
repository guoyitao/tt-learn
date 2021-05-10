package com.example.demo.service.ttl;

import com.example.demo.config.RabbitMQTTLConfiguration;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service()
public class TTLTopicOrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void mkOrder(String userId,String productId,int num){
        for (int i = 0; i < 10; i++) {
            String orderId = UUID.randomUUID().toString();
            rabbitTemplate.convertAndSend(RabbitMQTTLConfiguration.ORDER_DIRECT_EX,"ttl",orderId);
        }
    }


    public void mkTTLMessageOrder(String userId,String productId,int num){
        String orderId = UUID.randomUUID().toString();
        rabbitTemplate.convertAndSend(RabbitMQTTLConfiguration.ORDER_DIRECT_EX, "ttlmessage", orderId, getMessagePostProcessor());
    }

    private MessagePostProcessor getMessagePostProcessor() {
        return message -> {
            message.getMessageProperties().setExpiration("5000");
            message.getMessageProperties().setContentEncoding("UTF-8");
            return message;
        };
    }
}
