package com.lq.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.client
 * @className UserPointsClient
 * @description: 用户积分服务
 * @author: liqiang
 * @create: 2023-08-18 10:33
 **/
@FeignClient("user-points")
public interface UserPointsClient {


    @GetMapping("/deductUserPoints")
    public void deductUserPoints();
}
