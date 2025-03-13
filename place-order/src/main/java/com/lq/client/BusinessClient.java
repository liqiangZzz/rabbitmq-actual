package com.lq.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.client
 * @className BusinessClient
 * @description: 商家服务
 * @author: liqiang
 * @create: 2023-08-18 10:35
 **/
@FeignClient("business")
public interface BusinessClient {


    @GetMapping("/notify")
    public void notifyBusiness();
}
