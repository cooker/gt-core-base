package com.github.cooker.spring.ioc;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import java.lang.reflect.Method;

/**
 * grant
 * 3/5/2020 3:08 下午
 * 描述：
 */
public class MyBeanFactory implements FactoryBean<Maaa> {
    @Override
    public Maaa getObject() throws Exception {

        return (Maaa) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{Maaa.class}, new InvocationHandler(){

            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                if (method.getName().equals("say")){
                    System.out.println("sasasa");
                }else {
                    return method.invoke(o, objects);
                }
                return null;
            }
        });
    }

    @Override
    public Class<?> getObjectType() {
        return Maaa.class;
    }
}
