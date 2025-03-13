package com.lq.service;

import com.lq.entity.Resend;

import java.util.List;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.service.impl
 * @className ResendService
 * @description:
 * @author: liqiang
 * @create: 2023-08-18 15:57
 **/
public interface ResendService {
    /**
     * 查询全部
     * @return
     */
    public List<Resend> resendList();


    void insert(Resend resend);
}
