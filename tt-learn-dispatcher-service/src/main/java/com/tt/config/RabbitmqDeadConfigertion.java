package com.tt.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

//过期，消息就投递到死信队列
@Configuration
public class RabbitmqDeadConfigertion {

    public static final String ORDER_DIRECT_EX = "dead_exchange_order";
    public static final String DEAD_ROUTING_KEY = "dead";
    @Bean
    public DirectExchange deadEx() {
        return new DirectExchange(ORDER_DIRECT_EX, true, false);
    }

    @Bean
    public Queue deadQueue() {
        return new Queue("dead_order_fanout_queue", true);
    }

    //绑定到交换机
    @Bean
    public Binding deadQueueBinding() {
        return BindingBuilder.bind(deadQueue()).to(deadEx()).with(DEAD_ROUTING_KEY);
    }

    @Bean
    public DirectExchange orderEX() {
        return new DirectExchange("order_fanout_exchange", true, false);
    }
    @Bean
    public Queue orderQueue() {
        HashMap<String, Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange",RabbitmqDeadConfigertion.ORDER_DIRECT_EX); //死信队列，过期之后就放这
        args.put("x-dead-letter-routing-key",RabbitmqDeadConfigertion.DEAD_ROUTING_KEY);

        return new Queue("order_queue", true,false,false,args);
    }
    @Bean
    public Binding orderQueueBinding() {

        return BindingBuilder.bind(orderQueue()).to(orderEX()).with("");
    }


}
