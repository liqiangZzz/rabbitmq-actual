package com.lq.controller;

import com.lq.client.*;
import com.lq.config.DeadRabbitMQConfig;
import com.lq.config.DelayedRabbitMQConfig;
import com.lq.config.RabbitMQConfig;
import com.lq.utils.GlobalCacheUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.com.lq.controller
 * @className PlaceOrderController
 * @description: 下单服务
 * @author: liqiang
 * @create: 2023-08-18 10:16
 **/
@RestController
public class PlaceOrderController {

    @Autowired
    private ItemStockClient itemStockClient;
    @Autowired
    private OrderManageClient orderManageClient;
    @Autowired
    private CouponClient couponClient;
    @Autowired
    private UserPointsClient userPointsClient;
    @Autowired
    private BusinessClient businessClient;


    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 模拟用户下单操作
     *
     * @return
     */
    @GetMapping("/simulateUserPlaceOrder")
    public String simulateUserPlaceOrder() {
        long start = System.currentTimeMillis();
        //1、调用库存服务扣除商品库存
        itemStockClient.decr();
        //2、调用订单服务，创建订单
        orderManageClient.create();
        //3、调用优惠券服务，预扣除使用的优惠券
        couponClient.coupon();
        //4、调用用户积分服务，预扣除用户使用的积分
        userPointsClient.deductUserPoints();
        //5、调用商家服务，通知商家用户已下单
        businessClient.notifyBusiness();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return "place order is ok!";
    }


    @GetMapping("/simulateUserPlaceOrder2")
    public String simulateUserPlaceOrder2() {
        long start = System.currentTimeMillis();
        //1、调用库存服务扣除商品库存
        itemStockClient.decr();
        //2、调用订单服务，创建订单
        orderManageClient.create();
        //3、使用 mq 实现异步操作
        String userAndOrderInfo = "用户信息&订单信息&优惠券信息等等…………";

        rabbitTemplate.convertAndSend(RabbitMQConfig.PLACE_ORDER_EXCHANGE, "", userAndOrderInfo);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return "place order is ok!";
    }


    @GetMapping("/simulateUserPlaceOrder3")
    public String simulateUserPlaceOrder3() {
        long start = System.currentTimeMillis();
        //1、调用库存服务扣除商品库存
        itemStockClient.decr();
        //2、调用订单服务，创建订单
        orderManageClient.create();
        //3、使用 mq 实现异步操作
        String userAndOrderInfo = "用户信息&订单信息&优惠券信息等等…………";
        // 声明当前消息的id标识
        String id = UUID.randomUUID().toString();
        // 封装消息的全部信息
        Map map = new HashMap<>();
        map.put("message", userAndOrderInfo);
        map.put("exchange", RabbitMQConfig.PLACE_ORDER_EXCHANGE);
        map.put("routingKey", "");
        map.put("sendTime", new Date());
        GlobalCacheUtils.put(id, map);
        rabbitTemplate.convertAndSend(RabbitMQConfig.PLACE_ORDER_EXCHANGE, "", userAndOrderInfo, new CorrelationData(id));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return "place order is ok!";
    }


    @GetMapping("/simulateUserPlaceOrder4")
    public String simulateUserPlaceOrder4() {
        long start = System.currentTimeMillis();
        //1、使用 mq 死信队列完成延时  ，id作为消息发送
        String id = UUID.randomUUID().toString();
        rabbitTemplate.convertAndSend(DeadRabbitMQConfig.ORDINARY_PLACE_ORDER_EXCHANGE, "", id, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {

                message.getMessageProperties().setExpiration("15000");
                return message;
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return "dead place order is ok!";
    }

    @GetMapping("/simulateUserPlaceOrder5")
    public String simulateUserPlaceOrder5() {
        long start = System.currentTimeMillis();
        //1、使用 mq 死信队列完成延时  ，id作为消息发送
        String id = UUID.randomUUID().toString();
        rabbitTemplate.convertAndSend(DelayedRabbitMQConfig.DELAYED_ORDER_EXCHANGE, "delayed.abc", id, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setDelay(15000);
                return message;
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return "dead place order is ok!";
    }
}
