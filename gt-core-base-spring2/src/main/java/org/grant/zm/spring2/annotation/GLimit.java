package org.grant.zm.spring2.annotation;

/**
 * grant
 * 19/3/2020 5:59 下午
 * 描述：限流
 */
public @interface GLimit {
    String value() default "";
    int req() default -1;
    int time() default -1;
    GLimitCache cache() default GLimitCache.GUAVA;
}
