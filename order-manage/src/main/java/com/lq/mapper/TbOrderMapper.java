package com.lq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lq.entity.TbOrder;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.mapper
 * @className TbOrderMapper
 * @description: 下订单通知订单管理生产订单
 * @author: liqiang
 * @create: 2023-08-21 11:01
 **/
@Mapper
public interface TbOrderMapper extends BaseMapper<TbOrder> {
}
