package com.lq.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.client
 * @className ItemStockClient
 * @description: 库存
 * @author: liqiang
 * @create: 2023-08-18 10:29
 **/
@FeignClient("item-stock")
public interface ItemStockClient {

    @GetMapping("/decr")
    public void decr();
}
