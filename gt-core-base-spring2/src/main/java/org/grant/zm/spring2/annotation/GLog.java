package org.grant.zm.spring2.annotation;

import org.grant.zm.spring2.database.IGLogInsertHandler;

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

    String value() default "";

    GOperationType operationType();

    /**
     * 业务号
     * @return
     */
    String paramBusinessNoKey() default "businessNo";

    Class<IGLogInsertHandler> handler();
}
