package com.lq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lq.entity.Resend;
import com.lq.mapper.ResendMapper;
import com.lq.service.ResendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.service.impl
 * @className ResendServiceImpl
 * @description:
 * @author: liqiang
 * @create: 2023-08-18 15:58
 **/
@Service
public class ResendServiceImpl implements ResendService {

    @Autowired
    private ResendMapper resendMapper;

    @Override
    public List<Resend> resendList() {
        return resendMapper.selectList(new LambdaQueryWrapper<>());
    }

    @Override
    public void insert(Resend resend) {
        resendMapper.insert(resend);
    }
}
