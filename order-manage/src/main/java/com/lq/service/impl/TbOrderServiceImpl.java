package com.lq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lq.entity.TbOrder;
import com.lq.mapper.TbOrderMapper;
import com.lq.service.TbOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.service.impl
 * @className TbOrderServiceImpl
 * @description: 下订单通知订单管理生产订单
 * @author: liqiang
 * @create: 2023-08-21 11:03
 **/
@Slf4j
@Service
public class TbOrderServiceImpl implements TbOrderService {

    @Autowired
    private TbOrderMapper tbOrderMapper;

    @Override
    public int findById(String id) {
        return tbOrderMapper.selectCount(new LambdaQueryWrapper<TbOrder>().eq(TbOrder::getId, id));
    }

    @Override
    public void save(String id) {
        TbOrder tbOrder = new TbOrder();
        tbOrder.setOrderState(0);
        tbOrder.setTotal(BigDecimal.valueOf(55.5));
        tbOrder.setId(id);
        tbOrderMapper.insert(tbOrder);
    }
}
