package com.github.cooker.spring;

import com.github.cooker.spring.ioc.Maaa;
import org.junit.Test;
import org.springframework.cglib.core.ClassInfo;
import org.springframework.cglib.core.ReflectUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * grant
 * 3/5/2020 5:38 下午
 * 描述：
 */
public class ReflectionTest {

    @Test
    public void ss(){
        ClassInfo info = ReflectUtils.getClassInfo(Maaa.class);
        List<Method> methods = new ArrayList<>();
        ReflectUtils.addAllMethods(Maaa.class, methods);
        System.out.println(methods);
    }
}
