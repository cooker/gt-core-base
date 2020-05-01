package com.github.cooker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * grant
 * 27/4/2020 9:49 下午
 * 描述：
 */
@Controller
public class AA {

    @RequestMapping("/useraa")
    public String useraa(){
        return "useraa.html";
    }
}
