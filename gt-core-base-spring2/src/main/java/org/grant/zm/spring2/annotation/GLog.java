package org.grant.zm.spring2.annotation;

import org.grant.zm.spring2.database.GLogNopInsertHandler;
import org.grant.zm.spring2.database.IGLogInsertHandler;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * grant
 * 19/3/2020 5:57 下午
 * 描述：日志记录
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GLog {

    @AliasFor
    String value() default "";

    GOperationType operationType() default GOperationType.API;

    /**
     * 业务号
     * @return
     */
    String paramBusinessNoKey() default "businessNo";

    Class<? extends IGLogInsertHandler> handler() default GLogNopInsertHandler.class;
}
