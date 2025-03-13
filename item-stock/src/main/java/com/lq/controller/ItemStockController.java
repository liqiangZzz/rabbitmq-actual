package com.lq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.com.lq.controller
 * @className ItemStockController
 * @description: 库存服务
 * @author: liqiang
 * @create: 2023-08-18 10:21
 **/

@RestController
public class ItemStockController {

    private static int stock = 10;

    @GetMapping("/decr")
    public void decr() throws InterruptedException {
        Thread.sleep(400);
        stock--;
        if(stock < 0){
            throw new RuntimeException("商品库存不足！");
        }
        System.out.println("扣减库存成功！");
    }
}
