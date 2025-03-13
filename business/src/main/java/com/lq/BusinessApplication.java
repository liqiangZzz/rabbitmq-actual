package com.lq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq
 * @className BusinessApplication
 * @description: 商家服务
 * @author: liqiang
 * @create: 2023-08-17 14:02
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class BusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusinessApplication.class);
    }
}
