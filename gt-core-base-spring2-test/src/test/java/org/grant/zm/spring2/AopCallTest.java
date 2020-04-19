package org.grant.zm.spring2;

import lombok.extern.slf4j.Slf4j;
import org.grant.zm.spring2.aop.service.AopHelloService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * grant
 * 19/4/2020 6:40 下午
 * 描述：
 */
@Slf4j
public class AopCallTest extends BaseSpringAppTest{

    @Autowired
    AopHelloService aopHelloService;

    @Test
    public void kk(){
        aopHelloService.say("sasas");
        aopHelloService.say(1222);
    }
}
