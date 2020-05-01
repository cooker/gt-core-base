package com.github.cooker.controller;

import com.github.cooker.beans.MySession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

/**
 * grant
 * 28/4/2020 11:29 下午
 * 描述：
 */
@Slf4j
@RequestScope
@RestController
@RequestMapping("/mySession")
public class MySessionController {

    @Autowired
    MySession mySession;

    @GetMapping
    public MySession mySession(@RequestParam(value = "name", required = false) String name){
        log.info("请求：{}", this);
        if(null == name || "".equals(name)) {
            return mySession;
        }else {
            mySession.setName(name);
            return mySession;
        }
    }
}
