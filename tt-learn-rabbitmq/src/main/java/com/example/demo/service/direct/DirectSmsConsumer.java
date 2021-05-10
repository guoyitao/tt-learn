package com.example.demo.service.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service()
@RabbitListener(queues = "sms.direct.queue")
public class DirectSmsConsumer {

    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println(this.getClass().getSimpleName() +" get: "+ message);
    }
}
