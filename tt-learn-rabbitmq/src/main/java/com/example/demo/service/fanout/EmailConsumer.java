package com.example.demo.service.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service()
@RabbitListener(queues = "email.fanout.queue")
public class EmailConsumer {
    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println(this.getClass().getSimpleName() +" get: "+ message);
    }
}
