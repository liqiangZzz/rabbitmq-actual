package com.lq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.entity
 * @className UserPointsIdempotent
 * @description: 重复消费幂等表
 * @author: liqiang
 * @create: 2023-08-18 17:32
 **/
@Data
@TableName("tb_order")
public class TbOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 规定时间数据格式
     */
    @TableField("total")
    private BigDecimal total;


    @TableField("order_state")
    private Integer orderState;


}
