package com.lq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.controller
 * @className UserPointsController
 * @description: 用户积分服务
 * @author: liqiang
 * @create: 2023-08-18 10:24
 **/
@RestController
public class UserPointsController {

    @GetMapping("/deductUserPoints")
    public void deductUserPoints() throws InterruptedException {
        Thread.sleep(400);
        System.out.println("扣除用户积分成功！！");
    }
}
