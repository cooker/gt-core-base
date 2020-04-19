package org.grant.zm.spring2.aop;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.grant.zm.spring2.annotation.GLog;
import org.grant.zm.spring2.async.GAsyncCall;
import org.grant.zm.spring2.database.GLogEntity;
import org.grant.zm.spring2.database.IGLogInsertHandler;
import org.grant.zm.spring2.extend.GSpringHelper;
import org.grant.zm.spring2.extend.GSpringWebHelper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * grant
 * 19/3/2020 8:30 下午
 * 描述：日志切面
 */
@Aspect
public class GLogAspect {

    ThreadLocal<StopWatch> stopWatchThreadLocal = new ThreadLocal<>();
    ThreadLocal<Logger> loggerThreadLocal = new ThreadLocal<>();
    ThreadLocal<GLogEntity> gLogEntityThreadLocal = new ThreadLocal<>();

    @Autowired
    GAsyncCall asyncCall;
    @Autowired
    GSpringHelper springHelper;

    @Pointcut("@annotation(org.grant.zm.spring2.annotation.GLog)")
    public void log(){

    }

    @Before("log()")
    public void printLog(JoinPoint joinPoint){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        stopWatchThreadLocal.set(stopWatch);
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            String temp = null;
            Class targetClass = joinPoint.getTarget().getClass();
            Method method =((MethodSignature)joinPoint.getSignature()).getMethod();
            ReflectionUtils.makeAccessible(method);
            GLog gLog = method.getAnnotation(GLog.class);
            GLogEntity entity = makeGLogEntity(gLog);
            gLogEntityThreadLocal.set(entity);
            Field field = ReflectionUtils.findField(targetClass, "log", Logger.class);
            ReflectionUtils.makeAccessible(field);
            Logger log = (Logger) ReflectionUtils.getField(field, joinPoint.getTarget());
            loggerThreadLocal.set(log);
            log.info("{} >> {}", request.getMethod(), request.getRequestURI());
            temp = GSpringWebHelper.getRequestHeadersByJson(request);
            entity.setHeader(temp);
            log.info("header >>>> {}", temp);
            temp = GSpringWebHelper.getRequestParamsByJson(request);
            entity.setParams(temp);
            log.info("params >>>> {}", temp);
            log.info(" ======== body ======== ");
            String body = null;
            try {
                body = IOUtils.toString(request.getInputStream(), "utf-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            temp = body;
            entity.setBody(temp);
            log.info("{}", temp);
        }
    }

    private String getRealBusinessNo(String key, HttpServletRequest request){
        String businessNo = request.getParameter(key);
        if (StringUtils.isEmpty(businessNo)) {
            return DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS");
        }else {
            return businessNo;
        }
    }

    private GLogEntity makeGLogEntity(GLog gLog){
        GLogEntity entity = new GLogEntity();
        entity.setValue(gLog.value());
        entity.setOperationType(gLog.operationType());
        entity.setInsertHandler(gLog.handler());
        return entity;
    }

    @AfterReturning(value = "log()")
    public void printLog(){
        GLogEntity entityClone = null;
        try {
            StopWatch stopWatch = stopWatchThreadLocal.get();
            GLogEntity entity = gLogEntityThreadLocal.get();
            stopWatch.stop();
            entityClone = entity.clone();
            entityClone.setTime(stopWatch.getTotalTimeMillis());
            Logger logger = loggerThreadLocal.get();
            logger.info("{} 耗时：{} ms", entity.getValue(), stopWatch.getTotalTimeMillis());
        }finally {
            stopWatchThreadLocal.remove();
            gLogEntityThreadLocal.remove();
            loggerThreadLocal.remove();
            if (entityClone != null && entityClone.getInsertHandler() != null) {
                Class cl = entityClone.getInsertHandler();
                GLogEntity entity = entityClone;
                asyncCall.newCall(() ->{
                        IGLogInsertHandler igLogInsertHandler = (IGLogInsertHandler) springHelper.getBean(cl);
                        igLogInsertHandler.insert(entity);
                    }
                );
            }
        }
    }
}
