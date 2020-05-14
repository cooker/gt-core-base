package com.github.cooker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Hello world!
 *
 */
@EnableAsync
@SpringBootApplication
@ServletComponentScan
public class MybatisApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(MybatisApp.class, args);
    }
}
