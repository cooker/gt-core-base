package com.github.cooker.controller;

import com.baomidou.mybatisplus.core.override.MybatisMapperProxy;
import com.github.cooker.entity.User;
import com.github.cooker.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * grant
 * 22/4/2020 4:34 下午
 * 描述：
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserMapper userMapper;

    @GetMapping
    @ResponseBody
    public User findOne(@RequestParam("id") Integer id, HttpServletRequest request) {
        log.info("request Id:{}", id);
        return userMapper
                .selectById(id);
    }

    @RequestMapping("/id")
    public String ss() {
        return "forward:/user?id=1";
    }
}
