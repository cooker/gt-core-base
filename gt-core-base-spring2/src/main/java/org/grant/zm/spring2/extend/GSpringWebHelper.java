package org.grant.zm.spring2.extend;


import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.StringJoiner;

/**
 * ZoomGrant 2020/3/19 23:13
 */
public class GSpringWebHelper {

    public static String getRequestParamsByJson(HttpServletRequest request){
        Enumeration<String> parameterNames = request.getParameterNames();
        StringJoiner sj = new StringJoiner(",", "{", "}");
        String key = null, val = null;
        while (parameterNames.hasMoreElements()){
            key = parameterNames.nextElement();
            val = request.getParameter(key);
            sj.add("\"" + key + "\"" + ":" + "\"" + val + "\"");
        }
        return sj.toString();
    }

    public static String getRequestHeadersByJson(HttpServletRequest request){
        Enumeration<String> headerNames = request.getHeaderNames();
        StringJoiner sj = new StringJoiner(",", "{", "}");
        String key = null, val = null;
        while (headerNames.hasMoreElements()){
            key = headerNames.nextElement();
            val = request.getHeader(key);
            sj.add("\"" + key + "\"" + ":" + "\"" + val + "\"");
        }
        return sj.toString();
    }

    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes){
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        return null;
    }

}
