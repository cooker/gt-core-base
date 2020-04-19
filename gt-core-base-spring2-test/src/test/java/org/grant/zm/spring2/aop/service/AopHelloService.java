package org.grant.zm.spring2.aop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * grant
 * 19/4/2020 6:39 下午
 * 描述：
 */
@Slf4j
@Service
public class AopHelloService {

    public void say(String sa){
        log.info("String ====== ");
    }

    public void say(Integer sa){
        log.info("INT ====== ");
    }
}
