package org.grant.zm.lazy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * grant
 * 16/4/2020 11:21 上午
 * 描述：
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface GElastic {
    String propFile() default "application.properties";
    String propKey();
    String propVal();
    boolean matchIfMissing() default false;

    GElasticType[] success() default {};

    GElasticType[] failure() default {};
}
