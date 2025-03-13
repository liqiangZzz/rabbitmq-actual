package com.lq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lq.entity.Resend;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: rabbitmq-actual
 * @pageName com.lq.mapper
 * @className ResendMapper
 * @description: 存储发送失败信息
 * @author: liqiang
 * @create: 2023-08-18 15:54
 **/
@Mapper
public interface ResendMapper extends BaseMapper<Resend> {
}
