package com.github.cooker.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * grant
 * 26/4/2020 10:14 下午
 * 描述：
 */
@Service
public class Aservice {

    @Async
    public void say(){
        System.out.println("sasasas");
    }
}
