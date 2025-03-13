package com.lq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.entity
 * @className Resend
 * @description: 存储发送失败信息
 * @author: liqiang
 * @create: 2023-08-18 15:49
 **/
@Data
@TableName("resend")
public class Resend implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @TableField("message")
    private String message;

    @TableField("exchange")
    private String exchange;

    @TableField("routing_key")
    private String routingKey;

    /**
     * 规定时间数据格式
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("send_time")
    private Date sendTime;

    @TableField("send_count")
    private Integer sendCount;

    @TableField("is_send")
    private Integer isSend;
}
