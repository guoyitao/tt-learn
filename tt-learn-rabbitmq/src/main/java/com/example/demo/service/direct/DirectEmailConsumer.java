package com.example.demo.service.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service()
@RabbitListener(queues = "email.direct.queue")
public class DirectEmailConsumer {

    @Resource
    RedisTemplate redisTemplate;

    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println(this.getClass().getSimpleName() +" get: "+ message);
    }
}
