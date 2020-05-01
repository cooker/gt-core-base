package com.github.cooker.spring.beans;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.propertyeditors.ClassEditor;
import org.springframework.stereotype.Component;

/**
 * grant
 * 28/4/2020 9:22 下午
 * 描述：
 */
@Component
public class BabyService {

    public void say(){
        System.out.println("BabyService");
    }
}
