package com.lq.service;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.service
 * @className TbOrderService
 * @description: 下订单通知订单管理生产订单
 * @author: liqiang
 * @create: 2023-08-21 11:02
 **/
public interface TbOrderService {
    int findById(String id);

    void save(String id);
}
