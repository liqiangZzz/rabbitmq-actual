package com.lq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq
 * @className ItemStockApplication
 * @description: 商品库存 启动类
 * @author: liqiang
 * @create: 2023-08-17 13:42
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class ItemStockApplication {


    public static void main(String[] args) {
        SpringApplication.run(ItemStockApplication.class);
    }

}
