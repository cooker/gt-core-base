package com.github.cooker.entity.magic;

import com.github.cooker.entity.User;
import lombok.Data;

import java.util.List;

/**
 * grant
 * 22/4/2020 4:48 下午
 * 描述：
 */
@Data
public class ClassRoomDto {
    private Integer id;
    private List<User> users;
}
