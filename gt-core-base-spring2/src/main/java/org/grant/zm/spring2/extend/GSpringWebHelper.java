package org.grant.zm.spring2.extend;


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
            val = request.getHeader(key);
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

}
