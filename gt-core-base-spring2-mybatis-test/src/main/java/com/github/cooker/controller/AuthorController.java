package com.github.cooker.controller;

import com.github.cooker.entity.Author;
import com.github.cooker.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * grant
 * 13/5/2020 3:24 下午
 * 描述：
 */
@Controller
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorMapper authorMapper;

    @GetMapping
    @ResponseBody
    public Author find(@RequestParam("id") Integer id){
        return authorMapper.selectById(id);
    }
}
