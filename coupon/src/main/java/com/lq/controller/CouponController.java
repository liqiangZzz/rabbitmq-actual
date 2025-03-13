package com.lq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.controller
 * @className CouponController
 * @description: 优惠劵服务
 * @author: liqiang
 * @create: 2023-08-18 10:23
 **/
@RestController
public class CouponController {

    @GetMapping("/coupon")
    public void coupon() throws InterruptedException {
        Thread.sleep(400);
        System.out.println("优惠券预扣除成功！");
    }
}
