package com.github.cooker.spring.beans;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.support.AutowireCandidateResolver;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ClassUtils;

import javax.annotation.Resource;

/**
 * grant
 * 29/4/2020 1:51 下午
 * 描述：
 */
@Resource
public class ApplicationCtxListener implements AutowireCandidateResolver {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//        ctx.
    }
}
