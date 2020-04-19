package org.grant.zm.lazy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * grant
 * 16/4/2020 11:23 上午
 * 描述：
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE})
public @interface GElasticType {
    String typeName() default "org.grant.zm.lazy.GElasticNone";
    GElasticValue[] value() default {};
}
