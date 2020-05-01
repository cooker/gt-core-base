package com.github.cooker.controller;

import com.github.cooker.entity.User;
import com.github.cooker.entity.magic.ClassRoomDto;
import com.github.cooker.mapper.ClassRoomMapper;
import com.github.cooker.mapper.UserMapper;
import com.github.cooker.service.Aservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * grant
 * 22/4/2020 5:06 下午
 * 描述：
 */
@RestController
@RequestMapping("/classroom")
public class ClassRoomController {
    @Autowired
    ClassRoomMapper classRoomMapper;

    @Autowired
    Aservice aservice;

    @GetMapping
    public ClassRoomDto findPeople(@RequestParam("id") Integer id){
        aservice.say();
        return classRoomMapper.findPeople(id);
    }
}
