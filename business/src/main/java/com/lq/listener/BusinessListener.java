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
 * @className BusinessListener
 * @description: 商家服务消费
 * @author: liqiang
 * @create: 2023-08-18 11:44
 **/
@Slf4j
@Component
public class BusinessListener {

    /**
     * 监听RabbitMQ中的BUSINESS_QUEUE队列
     * 消费者方法，用于处理接收到的消息
     *
     * @param msg 消息内容
     * @param channel RabbitMQ通道
     * @param message 接收到的消息对象
     * @throws InterruptedException 线程睡眠中断异常
     * @throws IOException ACK确认时可能抛出的异常
     */
    @RabbitListener(queues = RabbitMQConfig.BUSINESS_QUEUE)
    public void consumer(String msg, Channel channel, Message message) throws InterruptedException, IOException {
        // 通知商家
        Thread.sleep(400);
        log.info("通知商家成功！" + msg);
        // 手动ACK
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
