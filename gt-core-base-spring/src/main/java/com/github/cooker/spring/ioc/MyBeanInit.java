package com.github.cooker.spring.ioc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import java.util.Set;


/**
 * grant
 * 2/5/2020 9:35 下午
 * 描述：
 */
@Slf4j
@Component
public class MyBeanInit implements ImportBeanDefinitionRegistrar {

//    @Override
//    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
//        log.info("MyBean beanFactory");
//        if (beanFactory instanceof ConfigurableListableBeanFactory ){
//            this.factory = (ConfigurableListableBeanFactory) beanFactory;
//        }
//    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
//        //扫描注解
//        Map<String, Object> attributes = metadata
//                .getAnnotationAttributes(MyBean.class.getName());
        System.out.println(registry.getClass().getName());
        MyBeanConfigure myBeanConfigure = new MyBeanConfigure(registry);
        myBeanConfigure.addIncludeFilter(new AnnotationTypeFilter(MyBean.class));
        Set<BeanDefinitionHolder> a = myBeanConfigure.doScan(this.getClass().getPackage().getName());
        for (BeanDefinitionHolder holder: a){
            holder.getBeanDefinition().setBeanClassName(MyBeanFactory.class.getName());
            System.out.println(holder.getBeanName());
        }
    }
}
