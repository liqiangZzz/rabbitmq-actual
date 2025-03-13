package com.lq.listener;

import com.lq.config.RabbitMQConfig;
import com.lq.service.UserPointsIdempotentService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.listener
 * @className UserPointsListener
 * @description: 用户积分服务
 * @author: liqiang
 * @create: 2023-08-18 13:42
 **/
@Slf4j
@Component
public class UserPointsListener {

    @Autowired
    private UserPointsIdempotentService userPointsIdempotentService;

    @RabbitListener(queues = RabbitMQConfig.USER_POINTS_QUEUE)
    public void consumer(String msg, Channel channel, Message message) throws IOException {
        // 通知商家
        MessageProperties messageProperties = message.getMessageProperties();
        // 获取生产者提供的CorrelationId要基于header去获取。
        String idName = "spring_returned_message_correlation";
        String id = messageProperties.getHeader(idName);
        //1、查询幂等表是否存在当前消息标识
        int count = userPointsIdempotentService.findById(id);
        //2、如果存在，直接return结束
        if(count == 1){
            log.info("消息已经被消费！！！无需重复消费！");
            return;
        }
        //3、如果不存在，插入消息标识到幂等表
        userPointsIdempotentService.save(id);
        //4、执行消费逻辑
        // 预扣除用户积分
        log.info("用户积分使用成功！" + msg);
        // 手动ACK
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        log.info("ack 确认确认成功！" + msg);
    }
}
