package org.grant.zm.spring2.config;

import org.grant.zm.spring2.async.GAsyncCall;
import org.grant.zm.spring2.extend.GRestHelper;
import org.grant.zm.spring2.extend.GSpringHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * grant
 * 14/4/2020 4:53 下午
 * 描述：
 */
@Configuration
public class GAutoCompentConfigure {

    @Bean
    public GAsyncCall gAsyncCall(){
        return new GAsyncCall();
    }

    @Bean
    public GSpringHelper gSpringHelper(){
        return new GSpringHelper();
    }

    @Bean
    public GRestHelper gRestHelper(){
        return new GRestHelper();
    }
}
