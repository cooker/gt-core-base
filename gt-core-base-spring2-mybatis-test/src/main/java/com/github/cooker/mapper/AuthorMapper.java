package com.github.cooker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.cooker.entity.Author;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * ZoomGrant 2020/5/12 20:26
 */
@Mapper
public interface AuthorMapper extends BaseMapper<Author> {

    Author selectId(@Param("id") Integer id);
}
