package com.lq.service;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.service
 * @className UserPointsIdempotentService
 * @description: 重复消费幂等
 * @author: liqiang
 * @create: 2023-08-18 17:39
 **/
public interface UserPointsIdempotentService {
    /**
     * 根据业务id 查询
     * @param id
     * @return
     */
    int findById(String id);

    /**
     * 如果幂等性表中数据不存在则新增
     * @param id
     */
    void save(String id);
}
