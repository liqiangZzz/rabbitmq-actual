package com.lq.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.client
 * @className CouponClient
 * @description: 优惠劵服务
 * @author: liqiang
 * @create: 2023-08-18 10:33
 **/
@FeignClient("coupon")
public interface CouponClient {

    @GetMapping("/coupon")
    public void coupon();
}
