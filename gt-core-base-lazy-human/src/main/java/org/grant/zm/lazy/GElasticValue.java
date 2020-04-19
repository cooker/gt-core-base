package org.grant.zm.lazy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * grant
 * 16/4/2020 11:33 上午
 * 描述：
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface GElasticValue {
    String key() default "";
    GElasticValueType keyType() default GElasticValueType.STRING;
    String value() default "";
}
