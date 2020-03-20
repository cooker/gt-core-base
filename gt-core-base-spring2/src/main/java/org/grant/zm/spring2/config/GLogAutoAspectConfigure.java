package org.grant.zm.spring2.config;

import org.grant.zm.spring2.aop.GLogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * grant
 * 20/3/2020 11:17 上午
 * 描述：
 */
@Configuration
public class GLogAutoAspectConfigure {

    @Bean
    public GLogAspect gLogAspect(){
        return new GLogAspect();
    }
}
