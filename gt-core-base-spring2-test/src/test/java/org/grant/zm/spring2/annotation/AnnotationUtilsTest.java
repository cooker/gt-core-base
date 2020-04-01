package org.grant.zm.spring2.annotation;

import org.springframework.core.annotation.AnnotationUtils;

/**
 * grant
 * 31/3/2020 11:49 上午
 * 描述：
 */
@MyAccotation
public class AnnotationUtilsTest {

    public static void main(String[] args) {
        MyAccotation myAccotation = AnnotationUtils.findAnnotation(AnnotationUtilsTest.class, MyAccotation.class);
        System.out.println(myAccotation.xx());
        System.out.println(myAccotation.value());
    }
}
