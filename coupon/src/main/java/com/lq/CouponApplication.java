package com.lq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq
 * @className CouponApplication
 * @description: 优惠劵服务
 * @author: liqiang
 * @create: 2023-08-17 13:55
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class CouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouponApplication.class);
    }
}
