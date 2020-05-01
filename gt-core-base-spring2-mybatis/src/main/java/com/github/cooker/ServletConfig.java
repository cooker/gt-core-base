package com.github.cooker;

import com.github.cooker.controller.MyServlet;
import com.github.cooker.filter.MyFilter;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * grant
 * 27/4/2020 6:36 上午
 * 描述：
 */
@Component
public class ServletConfig implements ServletContextInitializer
{
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        FilterRegistration.Dynamic dynamic = servletContext.addFilter("myFilter", MyFilter.class);
        dynamic.addMappingForUrlPatterns(null, false, "/*");
//        ServletContextListener

        ServletRegistration.Dynamic sa = servletContext.addServlet("myss", MyServlet.class);
        sa.addMapping("/myss");
        sa.setAsyncSupported(true);
    }



}
