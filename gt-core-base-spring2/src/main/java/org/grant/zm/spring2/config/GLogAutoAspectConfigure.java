package org.grant.zm.spring2.config;

import org.grant.zm.spring2.aop.GLogAspect;
import org.grant.zm.spring2.database.GLogNopInsertHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

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

    @Bean
    @Primary
    public GLogNopInsertHandler gLogNopInsertHandler(){
        return new GLogNopInsertHandler();
    }
}
