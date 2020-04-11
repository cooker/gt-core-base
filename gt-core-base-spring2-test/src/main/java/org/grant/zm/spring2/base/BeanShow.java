package org.grant.zm.spring2.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * grant
 * 10/4/2020 4:14 下午
 * 描述：
 */
@Slf4j
@Component
public class BeanShow implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        Map<String, IBean> a = event.getApplicationContext().getBeansOfType(IBean.class);
        for (String key : a.keySet()){
            log.info(">>>>>> {}", key);
        }
    }
}
