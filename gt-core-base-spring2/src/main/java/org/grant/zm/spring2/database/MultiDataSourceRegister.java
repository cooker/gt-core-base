package org.grant.zm.spring2.database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

/**
 * ZoomGrant 2020/3/21 10:22
 */
@Slf4j
public class MultiDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    @Override
    public void setEnvironment(Environment environment) {

    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {

    }
}
