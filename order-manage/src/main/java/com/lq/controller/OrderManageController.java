package com.lq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.controller
 * @className OrderManageController
 * @description: 订单服务
 * @author: liqiang
 * @create: 2023-08-18 10:22
 **/
@RestController
public class OrderManageController {


    @GetMapping("create")
    public void create() throws InterruptedException {
        Thread.sleep(400);
        System.out.println("创建订单成功！");
    }

}
