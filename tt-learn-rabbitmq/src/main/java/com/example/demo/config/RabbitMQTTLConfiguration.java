package com.example.demo.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

//消息会过期
@Configuration
public class RabbitMQTTLConfiguration {
    public static final String ORDER_DIRECT_EX = "ttl_direct_ex_order";

    @Bean
    public DirectExchange ttldirectExchange() {
        return new DirectExchange(ORDER_DIRECT_EX, true, false);
    }

    @Bean
    public Queue ttldirectsmsQueue() {
        HashMap<String, Object> args = new HashMap<>();
        args.put("x-message-ttl",5000); //过期时间 int类型
        args.put("x-max-length",5); //超过5条就进入死信队列
//        args.put("x-max-length-bytes",5);
        args.put("x-dead-letter-exchange",RabbitmqDeadConfigertion.ORDER_DIRECT_EX); //死信队列，过期之后就放这
        args.put("x-dead-letter-routing-key",RabbitmqDeadConfigertion.DEAD_ROUTING_KEY);
        return new Queue("ttl.direct.queue", true,false,false,args);
    }

    @Bean
    public Queue ttlmsgdirectsmsQueue() {
        return new Queue("ttl.msg.direct.queue", true);
    }

    //绑定到交换机
    @Bean
    public Binding ttldirectsmsBinding() {
        return BindingBuilder.bind(ttldirectsmsQueue()).to(ttldirectExchange()).with("ttl");
    }
    @Bean
    public Binding ttlmsgdirectsmsQueueBinding() {
        return BindingBuilder.bind(ttlmsgdirectsmsQueue()).to(ttldirectExchange()).with("ttlmessage");
    }
}
