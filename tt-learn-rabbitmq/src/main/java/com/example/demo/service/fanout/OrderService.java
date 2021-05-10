package com.example.demo.service.fanout;

import com.example.demo.config.RabbitmqFanoutConfiguration;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service()
public class OrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void mkOrder(String userId,String productId,int num){
        String orderId = UUID.randomUUID().toString();
        rabbitTemplate.convertAndSend(RabbitmqFanoutConfiguration.ORDER_FANOUT_EX,"",orderId);
    }

}
