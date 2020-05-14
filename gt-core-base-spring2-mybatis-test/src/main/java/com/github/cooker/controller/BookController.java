package com.github.cooker.controller;

import com.github.cooker.BeanLogConfig;
import com.github.cooker.entity.major.BookMajor;
import com.github.cooker.mapper.BookMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;

/**
 * ZoomGrant 2020/5/12 20:27
 */
@Slf4j
@Controller
@RequestMapping("/book")
public class BookController {

    private String name = "sss";
    @Autowired
    BookMapper bookMapper;

    @RequestMapping("/{id}")
    @ResponseBody
    public BookMajor findBookM(@PathVariable("id") Integer id){
        log.info("name={}", name);
        Object obj = BeanLogConfig.beanFactory.getBean("bookController");
        Field field = ReflectionUtils.findField(obj.getClass(), "name");
        field.setAccessible(true);
        try {
            field.set(obj, "2222");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return bookMapper.findBookM(id);
    }
}
