package org.grant.zm.spring2.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * grant
 * 31/3/2020 11:49 上午
 * 描述：
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MyAccotation {
    @AliasFor("xx")
    String value() default "";

    @AliasFor("value")
    String xx() default "";
}
