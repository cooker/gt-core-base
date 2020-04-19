package org.grant.zm.spring2;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * grant
 * 15/4/2020 7:24 PM
 * 描述：
 */
@Slf4j
@Component
@Data
public class TaskerService {

    @Autowired
    TaskScheduler scheduler;

    @Scheduled(cron = "0/2 * * * * ? ")
    public void say(){
        log.info("aaaa");
    }


    @Scheduled(cron = "0/2 * * * * ? ")
    public void saOk(){
        log.info("aaaa====");
    }
}
