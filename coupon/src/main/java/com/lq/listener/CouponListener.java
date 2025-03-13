package com.lq.listener;

import com.lq.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.listener
 * @className CouponListener
 * @description: 优惠卷服务
 * @author: liqiang
 * @create: 2023-08-18 13:42
 **/
@Slf4j
@Component
public class CouponListener {


    @RabbitListener(queues = RabbitMQConfig.COUPON_QUEUE)
    public void consumer(String msg, Channel channel, Message message) throws IOException, InterruptedException {
        // 通知商家
        Thread.sleep(400);
        log.info("使用优惠卷使用成功！" + msg);
        // 手动ACK
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
