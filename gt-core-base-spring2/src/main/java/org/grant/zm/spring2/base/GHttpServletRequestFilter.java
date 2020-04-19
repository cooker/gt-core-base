package org.grant.zm.spring2.base;

import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * grant
 * 14/4/2020 7:40 PM
 * 描述：
 */
public class GHttpServletRequestFilter extends HttpFilter {
    @Override
    public void destroy() { }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        InputStream inputStream = request.getInputStream();
        GHttpServletRequestWrapper requestWrapper = new GHttpServletRequestWrapper((HttpServletRequest)request);
        chain.doFilter(requestWrapper, response);
    }

    @Override
    public void init(FilterConfig filterConfig) { }
}
