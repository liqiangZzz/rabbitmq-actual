package com.lq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq
 * @className OrderManageApplication
 * @description: 订单管理服务
 * @author: liqiang
 * @create: 2023-08-17 13:51
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.lq.mapper")
public class OrderManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderManageApplication.class);
    }
}
