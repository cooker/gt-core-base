package com.github.cooker.spring;

import com.github.cooker.spring.ioc.Maaa;
import com.github.cooker.spring.ioc.MyBeanEntity;
import com.github.cooker.spring.ioc.MyBeanInitApplication;
import com.github.cooker.spring.ioc.Mysss;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * grant
 * 2/5/2020 9:39 下午
 * 描述：
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyBeanInitApplication.class)
public class MyBeanInitSpringTest {

//    @Autowired
//    MyBeanEntity myBeanEntity;
//    @Autowired
//    Mysss mysss;
    @Autowired(required = false)
    Maaa maaa;

    @Test
    public void sa(){
//        System.out.println(myBeanEntity);
//
//        System.out.println(mysss);
        maaa.say();
//        System.out.println(maaa);
    }
}
