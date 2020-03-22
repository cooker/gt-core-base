package org.grant.zm.spring2.aop;

import com.google.common.util.concurrent.RateLimiter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.grant.zm.spring2.annotation.GLimit;
import org.grant.zm.spring2.cache.GLimitCacheProcess;
import org.grant.zm.spring2.exception.LimitOverException;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * grant
 * 19/3/2020 8:30 下午
 * 描述：限流切面
 */
@Aspect
public class GLimitAspect {

    GLimitCacheProcess gLimitCacheProcess;

    public GLimitAspect(GLimitCacheProcess process){
        this.gLimitCacheProcess = process;
    }


    @Pointcut("@annotation(org.grant.zm.spring2.annotation.GLimit) || @within(org.grant.zm.spring2.annotation.GLimit)")
    public void limitCut(){

    }

    @Around("limitCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable
    {
        GLimit gLimit = getLimit(point);

        if (!Objects.isNull(gLimit))
        {
            RateLimiter rateLimiter = gLimitCacheProcess.getRateLimiter(gLimit.value());
            if (rateLimiter != null && !rateLimiter.tryAcquire()){
                throw new LimitOverException("服务请求超限 " + gLimit.req());
            }
        }

        try
        {
            return point.proceed();
        }catch (Throwable e){
            throw e;
        } finally {

        }
    }


    /**
     * 获取需要切换的数据源
     */
    public GLimit getLimit(ProceedingJoinPoint point)
    {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<? extends Object> targetClass = point.getTarget().getClass();
        GLimit gLimit = targetClass.getAnnotation(GLimit.class);
        if (!Objects.isNull(gLimit))
        {
            return gLimit;
        }
        else
        {
            Method method = signature.getMethod();
            gLimit = method.getAnnotation(GLimit.class);
            return gLimit;
        }
    }
}
