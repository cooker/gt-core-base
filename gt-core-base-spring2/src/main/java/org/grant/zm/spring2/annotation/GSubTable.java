package org.grant.zm.spring2.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * grant
 * 31/3/2020 11:39 上午
 * 描述：分表
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.CLASS)
public @interface GSubTable {
    @AliasFor("tableName")
    String value() default "";
    @AliasFor("value")
    String tableName() default "";

    GSubTableType subTableType() default GSubTableType.MONTH;
}
