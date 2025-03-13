package com.lq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq
 * @className PlaceOrderApplication
 * @description: 下单服务启动类
 * @author: liqiang
 * @create: 2023-08-17 11:14
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.lq.mapper")
public class PlaceOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlaceOrderApplication.class);
    }
}
