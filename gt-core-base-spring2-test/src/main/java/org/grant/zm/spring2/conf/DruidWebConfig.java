package org.grant.zm.spring2.conf;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * grant
 * 1/4/2020 10:48 上午
 * 描述：
 */
@Configuration
public class DruidWebConfig {

    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        //ServletRegistrationBean提供类的进行注册
        ServletRegistrationBean servletRegistrationBean =
                new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //添加初始化参数：initParams
        //白名单：
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        //IP黑名单（同时存在时，deny优先于allow）
        //如果满足deny，就提示：sorry，you are not permitted to view this page
        servletRegistrationBean.addInitParameter("deny", "192.168.1.73");
        //登录查看信息的账号和密码
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }
}
