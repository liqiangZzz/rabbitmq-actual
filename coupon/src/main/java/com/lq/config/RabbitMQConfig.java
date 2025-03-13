package com.lq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.config
 * @className RabbitMQConfig
 * @description: rabbitmq配置信息
 * @author: liqiang
 * @create: 2023-08-18 10:56
 **/
@Configuration
public class RabbitMQConfig {

    /**
     * 下单服务的交换机
     */
    public static final String PLACE_ORDER_EXCHANGE = "place_order_exchange";
    /**
     * coupon的Queue
     */
    public static final String COUPON_QUEUE = "coupon_queue";


    @Bean
    public Exchange placeOrderExchange() {
        return ExchangeBuilder.fanoutExchange(PLACE_ORDER_EXCHANGE).build();
    }

    @Bean
    public Queue couponQueue() {
        return QueueBuilder.durable(COUPON_QUEUE).build();
    }

    @Bean
    public Binding couponBinding(Queue couponQueue, Exchange placeOrderExchange) {
        return BindingBuilder.bind(couponQueue).to(placeOrderExchange).with("").noargs();
    }

}
