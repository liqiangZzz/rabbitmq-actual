package com.lq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lq.entity.UserPointsIdempotent;
import com.lq.mapper.UserPointsIdempotentMapper;
import com.lq.service.UserPointsIdempotentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.service.impl
 * @className UserPointsIdempotentImpl
 * @description: 重复消费幂等
 * @author: liqiang
 * @create: 2023-08-18 17:39
 **/
@Service
public class UserPointsIdempotentImpl implements UserPointsIdempotentService {

    @Autowired
    private UserPointsIdempotentMapper userPointsIdempotentMapper;

    @Override
    public int findById(String id) {
        return userPointsIdempotentMapper.selectCount(new LambdaQueryWrapper<UserPointsIdempotent>().eq(UserPointsIdempotent::getId,id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(String id) {
        UserPointsIdempotent userPointsIdempotent = new UserPointsIdempotent();
        userPointsIdempotent.setId(id);
        userPointsIdempotent.setCreateTime(new Date());
        userPointsIdempotentMapper.insert(userPointsIdempotent);
    }
}
