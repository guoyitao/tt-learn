package com.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//带key来辨别是哪个queue
@Configuration
public class RabbitmqDirectConfiguration {
    public static final String ORDER_DIRECT_EX = "order_direct_ex";

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(ORDER_DIRECT_EX, true, false);
    }

    @Bean
    public Queue directsmsQueue() {
        return new Queue("sms.direct.queue", true);
    }

    @Bean
    public Queue directwxQueue() {
        return new Queue("wx.direct.queue", true);
    }

    @Bean
    public Queue directemailQueue() {
        return new Queue("email.direct.queue", true);
    }

    //绑定到交换机

    @Bean
    public Binding directsmsBinding() {
        return BindingBuilder.bind(directsmsQueue()).to(directExchange()).with("sms");
    }

    @Bean
    public Binding directwxBinding() {
        return BindingBuilder.bind(directwxQueue()).to(directExchange()).with("wx");
    }

    @Bean
    public Binding directemailBinding() {
        return BindingBuilder.bind(directemailQueue()).to(directExchange()).with("email");
    }

}
