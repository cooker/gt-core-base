package org.grant.zm.spring2.aop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.grant.zm.spring2.annotation.GDataSource;
import org.grant.zm.spring2.database.MultiDataSourceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * grant
 * 20/3/2020 10:28 上午
 * 描述：
 */
@Order(1)
@Aspect
@Slf4j
@Data
@AllArgsConstructor
public class GDataSourceAspect {

    MultiDataSourceHandler multiDataSourceHandler;

    @Pointcut("@annotation(org.grant.zm.spring2.annotation.GDataSource)"
            + "|| @within(org.grant.zm.spring2.annotation.GDataSource)")
    public void dsPointCut()
    {

    }

    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable
    {
        GDataSource dataSource = getDataSource(point);

        if (!Objects.isNull(dataSource))
        {
            if (dataSource.showLog()){
                log.info("数据源切换 {}#{}===> {}",point.getTarget().getClass().getName(), point.getSignature().getName(), dataSource.value());
            }
            multiDataSourceHandler.setDataSourceName(dataSource.value());
        }

        try
        {
            return point.proceed();
        }
        finally
        {
            // 销毁数据源 在执行方法之后
            multiDataSourceHandler.clearDataSourceName();
        }
    }

    /**
     * 获取需要切换的数据源
     */
    public GDataSource getDataSource(ProceedingJoinPoint point)
    {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<? extends Object> targetClass = point.getTarget().getClass();
        GDataSource targetDataSource = targetClass.getAnnotation(GDataSource.class);
        if (!Objects.isNull(targetDataSource))
        {
            return targetDataSource;
        }
        else
        {
            Method method = signature.getMethod();
            GDataSource dataSource = method.getAnnotation(GDataSource.class);
            return dataSource;
        }
    }


}
