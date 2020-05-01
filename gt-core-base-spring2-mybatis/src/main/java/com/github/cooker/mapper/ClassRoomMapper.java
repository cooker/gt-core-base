package com.github.cooker.mapper;

import com.github.cooker.entity.ClassRoom;
import com.github.cooker.entity.magic.ClassRoomDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * grant
 * 22/4/2020 4:20 下午
 * 描述：
 */
@Mapper
public interface ClassRoomMapper {

    ClassRoom findOne(Integer id);

    ClassRoomDto findPeople(Integer id);
}
