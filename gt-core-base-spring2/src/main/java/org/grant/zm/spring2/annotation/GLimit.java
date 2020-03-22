package org.grant.zm.spring2.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * grant
 * 19/3/2020 5:59 下午
 * 描述：限流
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface GLimit {
    String value();
    double req() default 20.0;
    GLimitCache cache() default GLimitCache.GUAVA;
}
