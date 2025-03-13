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
     * business 的Queue
     */
    public static final String BUSINESS_QUEUE = "business_queue";


    /**
     * 创建一个用于下单的Exchange
     *
     * @return Exchange 实例，用于处理订单相关的消息路由
     */
    @Bean
    public Exchange placeOrderExchange() {
        return ExchangeBuilder.fanoutExchange(PLACE_ORDER_EXCHANGE).build();
    }


    /**
     * 创建一个名为businessQueue的Bean，该Bean是一个持久化的队列
     * 用于处理业务消息持久化意味着队列在RabbitMQ重启后仍然存在
     *
     * @return 返回一个持久化的Queue对象，队列名称为BUSINESS_QUEUE
     */
    @Bean
    public Queue businessQueue() {
        return QueueBuilder.durable(BUSINESS_QUEUE).build();
    }

    /**
     * 配置并创建一个绑定，将业务队列绑定到下单交换机
     * 此绑定没有特定的路由键，意味着所有发送到交换机的消息都将被此队列接收
     *
     * @param businessQueue 业务队列，代表处理业务逻辑的队列
     * @param placeOrderExchange 下单交换机，代表处理下单消息的交换机
     * @return 返回配置好的绑定
     */
    @Bean
    public Binding businessBinding(Queue businessQueue, Exchange placeOrderExchange) {
        return BindingBuilder.bind(businessQueue).to(placeOrderExchange).with("").noargs();
    }

}
