package org.grant.zm.spring2.aop;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.grant.zm.spring2.annotation.GLog;
import org.grant.zm.spring2.async.GAsyncCall;
import org.grant.zm.spring2.database.GLogEntity;
import org.grant.zm.spring2.database.IGLogInsertHandler;
import org.grant.zm.spring2.extend.GSpringHelper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
import java.util.Enumeration;
import java.util.StringJoiner;

/**
 * grant
 * 19/3/2020 8:30 下午
 * 描述：日志切面
 */
@Aspect
@Component
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
            Enumeration<String> headerNames = request.getHeaderNames();
            Enumeration<String> parameterNames = request.getParameterNames();
            String key = null, val = null;
            Object[] args = joinPoint.getArgs();
            Class<?>[] argTypes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                argTypes[i] = args[i].getClass();
            }
            String temp = null;
            try {
                Class targetClass = joinPoint.getTarget().getClass();
                Method method = ReflectionUtils.findMethod(targetClass, joinPoint.getSignature().getName(), argTypes);
                ReflectionUtils.makeAccessible(method);
                GLog gLog = method.getAnnotation(GLog.class);
                GLogEntity entity = makeGLogEntity(gLog);
                Field field = ReflectionUtils.findField(targetClass, "log", Logger.class);
                ReflectionUtils.makeAccessible(field);
                Logger log = (Logger) ReflectionUtils.getField(field, joinPoint.getTarget());
                loggerThreadLocal.set(log);
                log.info("{} >> {}", request.getMethod(), request.getRequestURI());
                StringJoiner sj = new StringJoiner(",", "{", "}");
                while (headerNames.hasMoreElements()){
                    key = headerNames.nextElement();
                    val = request.getHeader(key);
                    sj.add(key + "=" + val);
                }
                temp = sj.toString();
                entity.setHeader(temp);
                log.info("header >>>> {}", temp);
                sj = new StringJoiner(",", "{", "}");
                while (parameterNames.hasMoreElements()) {
                    key = parameterNames.nextElement();
                    val = request.getParameter(key);
                    sj.add(key + "=" + val);
                }
                temp = sj.toString();
                entity.setParams(temp);
                log.info("params >>>> {}", temp);
                log.info(" ======== body ======== ");
                String body = IOUtils.toString(request.getInputStream(), "utf-8");
                temp = body;
                entity.setBody(body);
                log.info("{}", temp);
                gLogEntityThreadLocal.set(entity);
            } catch ( IOException e) {
                e.printStackTrace();
            }
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
                Class<IGLogInsertHandler> cl = entityClone.getInsertHandler();
                GLogEntity entity = entityClone;
                asyncCall.newCall(new GAsyncCall.Call() {
                    @Override
                    public void call() {
                        IGLogInsertHandler igLogInsertHandler = springHelper.getBean(cl);
                        igLogInsertHandler.insert(entity);
                    }
                });
            }
        }
    }
}
