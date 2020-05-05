package com.github.cooker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.cooker.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;

/**
 * grant
 * 22/4/2020 4:16 下午
 * 描述：
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User findOne(Integer id);
    User selectById(Integer id, Integer xid);
}
