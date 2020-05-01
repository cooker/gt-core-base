package org.grant.zm.spring2.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * grant
 * 19/4/2020 6:41 下午
 * 描述：
 */
@Slf4j
@Aspect
@Component
public class AopAspect {

    @Pointcut(value = "execution(* org.grant.zm.spring2.aop.service..*.say(String))")
    public void cut() {

    }


    @Before("cut()")
    public void beforeCut(){
        log.info(">>>>> AOP 切入");
    }
}
