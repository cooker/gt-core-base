package com.github.cooker;

import com.github.cooker.filter.MyFilter;
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
public class WebApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(WebApp.class, args);
    }



//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean  bean = new FilterRegistrationBean(new MyFilter());
//        //bean.addUrlPatterns(new String[]{"*.do","*.jsp"});
//        bean.addUrlPatterns("/sa/*");
//        return bean;
//    }
//
//    @Bean
//    public ServletRegistrationBean servletRegistrationBean() {
//        ServletRegistrationBean  bean = new ServletRegistrationBean(new MyServlet());
//        //bean.addUrlPatterns(new String[]{"*.do","*.jsp"});
//        bean.addUrlMappings("/my/*");
//        bean.setAsyncSupported(true);
//        return bean;
//    }
}
