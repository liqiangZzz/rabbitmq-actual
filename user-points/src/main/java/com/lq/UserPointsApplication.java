package com.lq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq
 * @className UserPoinApplication
 * @description: 用户积分服务
 * @author: liqiang
 * @create: 2023-08-17 13:59
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.lq.mapper")
public class UserPointsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserPointsApplication.class);
    }
}
