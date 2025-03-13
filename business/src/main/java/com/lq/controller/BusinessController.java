package com.lq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.controller
 * @className BusinessController
 * @description: 商家服务
 * @author: liqiang
 * @create: 2023-08-18 10:25
 **/
@RestController
public class BusinessController {

    @GetMapping("/notify")
    public void notifyBusiness() throws InterruptedException {
        Thread.sleep(400);
        System.out.println("通知商家成功！！");
    }
}
