package com.github.cooker.spring.beans;

import com.github.cooker.spring.entity.Baby;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * grant
 * 28/4/2020 9:25 下午
 * 描述：
 */
@Component
public class BabyFactoryBean implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        Baby baby = new Baby();
        baby.setName("BabyBeanFactory baby");
        return baby;
    }

    @Override
    public Class<?> getObjectType() {
        return Baby.class;
    }
}
