package com.github.cooker.entity;

import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * grant
 * 27/4/2020 12:55 下午
 * 描述：
 */
public class UserTest {

    @Test
    public void sa() throws IntrospectionException {
        User user = new User();

        BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            //需要注意参数类型匹配问题，此问题不在本文讨论范围里面
            System.out.println(pd.getName());
        }
    }
}
