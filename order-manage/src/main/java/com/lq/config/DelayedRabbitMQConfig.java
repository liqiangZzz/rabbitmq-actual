package com.lq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.config
 * @className DelayedRabbitMQConfig
 * @description: 延时交换机配置
 * @author: liqiang
 * @create: 2023-08-21 11:45
 **/
@Configuration
public class DelayedRabbitMQConfig {


    public static final String DELAYED_ORDER_EXCHANGE = "delayed_order_exchange";
    public static final String DELAYED_ORDER_QUEUE = "delayed_order_queue";
    public static final String DELAYED_ORDER_KEY = "delayed.#";

    @Bean
    public Exchange delayedExChange() {
        HashMap<String, Object> arguments = new HashMap<>();
        arguments.put("x-delayed-type","topic");
        return new CustomExchange(DELAYED_ORDER_EXCHANGE, "x-delayed-message", true, false, arguments);
    }


    @Bean
    public Queue delayQueue() {
        return QueueBuilder.durable(DELAYED_ORDER_QUEUE).build();
    }

    @Bean
    public Binding delayBing(Queue delayQueue, Exchange delayedExChange) {
        return BindingBuilder.bind(delayQueue).to(delayedExChange).with(DELAYED_ORDER_KEY).noargs();
    }
}
