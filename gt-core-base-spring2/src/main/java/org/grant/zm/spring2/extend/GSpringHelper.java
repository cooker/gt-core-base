package org.grant.zm.spring2.extend;

import org.aspectj.lang.JoinPoint;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * grant
 * 19/3/2020 9:31 下午
 * 描述：
 */
@Component
public class GSpringHelper implements ApplicationContextAware {
    static ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    public static <T> T getBean(Class<T> cl){
        return context.getBean(cl);
    }

    public static Object getBean(String beanName){
        return context.getBean(beanName);
    }

    public static String[] getBeanNamesForType(Class<?> cl){
        return context.getBeanNamesForType(cl);
    }

    public static Resource getResource(String s){
        return context.getResource(s);
    }

    public static Resource[] getResources(String s) throws IOException {
        return context.getResources(s);
    }

    public static Class<?>[] getArgTypes(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Class<?>[] argTypes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }
        return argTypes;
    }
}
