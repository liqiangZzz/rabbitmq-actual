package com.lq.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.client
 * @className OrderManageClient
 * @description: 订单服务
 * @author: liqiang
 * @create: 2023-08-18 10:31
 **/
@FeignClient("order-manage")
public interface OrderManageClient {

    @GetMapping("create")
    public void create();
}
