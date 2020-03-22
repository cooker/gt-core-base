package org.grant.zm.spring2.config;

import org.grant.zm.spring2.aop.GLimitAspect;
import org.grant.zm.spring2.cache.GLimitCacheProcess;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * ZoomGrant 2020/3/22 18:04
 */
@Configurable
@Import(GLimitCacheProcess.class)
public class GAutoLimitConfigure {
    @Bean
    public GLimitAspect gLimitAspect(GLimitCacheProcess process){
        return new GLimitAspect(process);
    }
}
