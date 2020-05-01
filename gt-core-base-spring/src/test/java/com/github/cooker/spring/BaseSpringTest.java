package com.github.cooker.spring;

import com.github.cooker.spring.beans.BabyService;
import com.github.cooker.spring.entity.Baby;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * grant
 * 28/4/2020 9:18 下午
 * 描述：
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationScan.class)
//@SpringJUnitConfig(ApplicationScan.class)
public class BaseSpringTest {

    @Autowired(required = false)
    BabyService babyService;


    @Autowired(required = false)
    Baby baby;

    @Test
    public void babyService(){
        Assert.assertNotNull(babyService);
        babyService.say();
    }

    @Test
    public void babyBeanFactory(){
        Assert.assertNotNull(baby);
        System.out.println(baby.getName());
    }
}
