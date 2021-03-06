package com.github.cooker.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.cooker.entity.Book;
import com.github.cooker.entity.major.BookMajor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * ZoomGrant 2020/5/12 20:26
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {

    BookMajor findBookM(@Param("id")Integer id);
}
