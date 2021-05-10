package com.example.demo.service.direct;

import com.example.demo.config.RabbitmqDirectConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service()
public class DirectOrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void mkOrder(String userId,String productId,int num){
        String orderId = UUID.randomUUID().toString();
        rabbitTemplate.convertAndSend(RabbitmqDirectConfiguration.ORDER_DIRECT_EX,"wx",orderId);
        rabbitTemplate.convertAndSend(RabbitmqDirectConfiguration.ORDER_DIRECT_EX,"sms",orderId);
        rabbitTemplate.convertAndSend(RabbitmqDirectConfiguration.ORDER_DIRECT_EX,"email",orderId);
    }
}
