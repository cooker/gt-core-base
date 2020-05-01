package com.github.cooker.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import java.io.IOException;

/**
 * grant
 * 26/4/2020 10:28 下午
 * 描述：
 */
@WebFilter(value = "/sa/*")
@Component
public class MyFilter extends HttpFilter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("========" + request.getDispatcherType().name());
        String id = request.getParameter("id");
        if ("2".equalsIgnoreCase(id)){
            response.getWriter().println("sasasa");
        }else {
            chain.doFilter(request, response);
        }
    }
}
