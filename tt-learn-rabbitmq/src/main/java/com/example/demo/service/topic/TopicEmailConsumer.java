package com.example.demo.service.topic;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Service;

@Service()
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "email.topic.queue",declare = "true",autoDelete = "false"),
        exchange = @Exchange(value = "topic_order_exchange",type = ExchangeTypes.TOPIC),
        key = "*.email.#"
))
public class TopicEmailConsumer {
    @RabbitHandler
    public void reviceMessage(String message){
        System.out.println(this.getClass().getSimpleName() +" get: "+ message);
    }
}
