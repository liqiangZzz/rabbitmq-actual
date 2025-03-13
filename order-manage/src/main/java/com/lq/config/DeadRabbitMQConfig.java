package com.lq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.config
 * @className DeadRabbitMQConfig
 * @description: 死信队列，实现延时
 * @author: liqiang
 * @create: 2023-08-21 11:06
 **/
@Configuration
public class DeadRabbitMQConfig {


    /**
     * 订单死信队列
     */
    public static final String DEAD_ORDER_EXCHANGE = "dead_order_exchange";
    public static final String DEAD_ORDER_QUEUE = "dead_order_queue";

    @Bean
    public Exchange deadOrderExchange() {
        return ExchangeBuilder.fanoutExchange(DEAD_ORDER_EXCHANGE).build();
    }

    @Bean
    public Queue deadOrderQueue() {
        return QueueBuilder.durable(DEAD_ORDER_QUEUE).build();
    }

    @Bean
    public Binding deadBinding(Exchange deadOrderExchange, Queue deadOrderQueue) {
        return BindingBuilder.bind(deadOrderQueue).to(deadOrderExchange).with("").noargs();
    }
}
