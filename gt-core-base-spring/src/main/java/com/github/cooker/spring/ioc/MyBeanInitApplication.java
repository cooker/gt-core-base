package com.github.cooker.spring.ioc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * grant
 * 2/5/2020 9:38 下午
 * 描述：
 */
@Configuration
@Import(MyBeanInit.class)
@ComponentScan
public class MyBeanInitApplication {

}
