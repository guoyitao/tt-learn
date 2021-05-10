package com.example.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//过期，消息就投递到死信队列
@Configuration
public class RabbitmqDeadConfigertion {

    public static final String ORDER_DIRECT_EX = "dead_ex_order";
    public static final String DEAD_ROUTING_KEY = "dead";
    @Bean
    public DirectExchange deadEx() {
        return new DirectExchange(ORDER_DIRECT_EX, true, false);
    }

    @Bean
    public Queue deadQueue() {
        return new Queue("dead.direct.queue", true);
    }

    //绑定到交换机
    @Bean
    public Binding deadQueueBinding() {

        return BindingBuilder.bind(deadQueue()).to(deadEx()).with(DEAD_ROUTING_KEY);
    }
}
