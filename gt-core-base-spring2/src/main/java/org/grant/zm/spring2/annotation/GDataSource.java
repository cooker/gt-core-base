package org.grant.zm.spring2.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * grant
 * 20/3/2020 9:43 上午
 * 描述：
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface GDataSource {
    String value() default "";
    boolean showLog() default true;
}
