package org.grant.zm.spring2.extend;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * grant
 * 19/3/2020 9:31 下午
 * 描述：
 */
@Component
public class GSpringHelper implements ApplicationContextAware {
    ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public <T> T getBean(Class<T> cl){
        return context.getBean(cl);
    }

    public Object getBean(String beanName){
        return context.getBean(beanName);
    }
}
