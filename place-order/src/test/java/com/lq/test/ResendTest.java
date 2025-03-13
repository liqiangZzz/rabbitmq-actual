package com.lq.test;

import com.lq.service.ResendService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.test
 * @className ResendTest
 * @description:
 * @author: liqiang
 * @create: 2023-08-18 16:05
 **/
@SpringBootTest
public class ResendTest {

    @Autowired
    private ResendService resendService;

    @Test
    public void testQuery(){
        System.out.println(resendService.resendList());
    }
}
