package com.lq.config;

import com.lq.entity.Resend;
import com.lq.service.ResendService;
import com.lq.utils.GlobalCacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.Map;


/**
 * @program: rabbitmq-actual
 * @pageName com.lq.config
 * @className RabbitTemplateConfig
 * @description: RabbitTemplate 配置信息
 * @author: liqiang
 * @create: 2023-08-18 14:02
 **/
@Slf4j
@Configuration
public class RabbitTemplateConfig {


    @Autowired
    private ResendService resendService;

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        //1、创建
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        //2、将connectionFactory设置到RabbitTemplate对象中
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //3、设置confirm 机制
        rabbitTemplate.setConfirmCallback(this.confirmCallback());
        //4、 设置 returns 机制
        rabbitTemplate.setReturnCallback(this.returnCallback());
        //5、设置mandatory为true
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }


    public RabbitTemplate.ConfirmCallback confirmCallback() {
        return new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (correlationData == null) {
                    return;
                }
                //获取唯一标记
                String msgId = correlationData.getId();
                if (ack) {
                    Object value = GlobalCacheUtils.get(msgId);
                    log.info("消息发送到Exchange成功!! msgId {} ", msgId);
                    log.info("消息发送到Exchange成功!! value {} ", value);
                    GlobalCacheUtils.remove(msgId);
                } else {
                    log.info("消息发送到Exchange失败!! msgId {} cause:{}", msgId, cause);
                    Map<String,Object> map = (Map) GlobalCacheUtils.get(msgId);
                    Resend resend = new Resend();
                    resend.setId(msgId);
                    resend.setExchange(map.get("exchange").toString());
                    resend.setRoutingKey(map.get("routingKey").toString());
                    resend.setMessage(map.get("message").toString());
                    resend.setSendTime(new Date(map.get("sendTime").toString()));
                    resendService.insert(resend);
                }
            }
        };
    }


    public RabbitTemplate.ReturnCallback returnCallback() {
        return new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                log.info("消息未路由到队列");
                log.info("return：唯一标识：{}", message.getMessageProperties().getClusterId());
                log.info("return：消息为：{}", new String(message.getBody()));
                log.info("return：交换机为：{}", exchange);
                log.info("return：路由为：{}", routingKey);
            }
        };
    }
}
