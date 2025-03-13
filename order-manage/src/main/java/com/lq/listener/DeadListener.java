package com.lq.listener;

import com.lq.config.DeadRabbitMQConfig;
import com.lq.service.TbOrderService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.listener
 * @className DeadListener
 * @description: 下订单通知订单管理生产订单
 * @author: liqiang
 * @create: 2023-08-21 11:19
 **/
@Slf4j
@Component
public class DeadListener {


    @Autowired
    private TbOrderService tbOrderService;


    /**
     * 监听死信队列，通过死信队列通知生产订单
     */
    @RabbitListener(queues = DeadRabbitMQConfig.DEAD_ORDER_QUEUE)
    public void consumer(Channel channel, Message message) throws IOException {
        //获取信息
        String id = new String(message.getBody());
        //1、查查看是否已经生成订单
        int count = tbOrderService.findById(id);
        //2、如果存在，直接return结束
        if (count == 1) {
            log.info("订单已生成！！！无需重复生成订单！");
            return;
        }
        //3、如果不存在，插入消息标识到订单表中
        tbOrderService.save(id);
        //4、执行生成订单
        log.info("订单生成功！" + new String(message.getBody()));
        // 手动ACK
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);

        log.info("ack 订单生成功！" + new String(message.getBody()));
    }
}
