package com.lq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lq.entity.UserPointsIdempotent;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.mapper
 * @className UserPointsIdempotent
 * @description:重复消费幂等
 * @author: liqiang
 * @create: 2023-08-18 17:35
 **/
@Mapper
public interface UserPointsIdempotentMapper extends BaseMapper<UserPointsIdempotent> {
}
