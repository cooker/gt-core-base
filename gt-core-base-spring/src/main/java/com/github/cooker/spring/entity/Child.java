package com.github.cooker.spring.entity;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * grant
 * 29/4/2020 3:22 下午
 * 描述：
 */
@Component
public class Child implements People, InitializingBean {
    @PostConstruct
    public void init(){
        System.out.println("Baby 初始化");
    }

    @PreDestroy
    public void destory(){
        System.out.println("Baby 销毁");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Baby 属性设置后");
    }
}
