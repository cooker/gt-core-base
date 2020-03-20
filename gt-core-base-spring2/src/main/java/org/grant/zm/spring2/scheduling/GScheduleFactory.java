package org.grant.zm.spring2.scheduling;

import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * grant
 * 20/3/2020 2:40 下午
 * 描述：
 */
public class GScheduleFactory {

    public static ScheduledTaskRegistrar newTaskRegistrar(){
        return new ScheduledTaskRegistrar();
    }
}
