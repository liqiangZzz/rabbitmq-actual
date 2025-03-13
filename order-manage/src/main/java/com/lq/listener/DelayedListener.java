package com.lq.listener;

import com.lq.config.DelayedRabbitMQConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.listener
 * @className DelayedListener
 * @description: 监听延迟队列
 * @author: liqiang
 * @create: 2023-08-21 13:20
 **/
@Slf4j
@Component
public class DelayedListener {


    @RabbitListener(queues = DelayedRabbitMQConfig.DELAYED_ORDER_QUEUE)
    public void consumer(Channel channel, Message message) throws IOException, InterruptedException {
        log.info("开始执行业务！" + new String(message.getBody()));
        Thread.sleep(400);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        log.info("ack 订单生成功！" + new String(message.getBody()));
    }
}
