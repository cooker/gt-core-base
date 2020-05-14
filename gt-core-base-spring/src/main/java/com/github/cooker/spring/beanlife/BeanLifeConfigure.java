package com.github.cooker.spring.beanlife;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * grant
 * 11/5/2020 4:21 下午
 * 描述：
 */
@Slf4j
@Configuration
@ComponentScan
public class BeanLifeConfigure implements InstantiationAwareBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        log.info("{} >>>> {}", beanClass, beanName);
        if (beanClass == QHello.class){
            QHello hello = new QHello();
            hello.setNum(12);
            return hello;
        }
        return null;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        log.info(">>>> postProcessProperties");
//        BeanFactory
        return null;
    }


}
