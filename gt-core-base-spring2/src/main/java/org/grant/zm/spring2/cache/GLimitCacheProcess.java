package org.grant.zm.spring2.cache;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.grant.zm.spring2.annotation.GLimit;
import org.grant.zm.spring2.base.BaseController;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ZoomGrant 2020/3/22 16:53
 * 緩存 +++
 */
@Slf4j
public final class GLimitCacheProcess implements BeanPostProcessor, ApplicationContextAware, Ordered {

    private Set<String> setLimits = null;
    private Map<String, RateLimiter>  rateLimiterMap = null;

    @PostConstruct
    public void init(){
        setLimits = new HashSet<>();
        rateLimiterMap = new HashMap<>();
    }

    public RateLimiter getRateLimiter(String cacheName){
        return rateLimiterMap.get(cacheName);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof BaseController) {
            GLimit gLimit = AnnotationUtils.<GLimit>findAnnotation(bean.getClass(), GLimit.class);
            if (gLimit == null) {
                Method[] methods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
                for (Method method : methods) {
                     gLimit = AnnotationUtils.findAnnotation(method, GLimit.class);
                     if (gLimit == null) continue;
                     this.addLimiter(gLimit);
                }
            }else {
                this.addLimiter(gLimit);
            }
        }
        return bean;
    }

    private void addLimiter(GLimit gLimit) {
        if (StringUtils.isEmpty(gLimit.value())) throw new IllegalArgumentException("限流名称为空");
        if (setLimits.add(gLimit.value())) {
            rateLimiterMap.put(gLimit.value(), RateLimiter.create(gLimit.req()));
        }
    }

    private String[] getParamTypes(Class[] cls){
        return Arrays.asList(cls).stream().map(Class::getName).collect(Collectors.toList()).toArray(new String[0]);
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        log.info("+++++++++ 限流服务加载 +++++++++");
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
