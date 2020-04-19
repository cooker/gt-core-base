package org.grant.zm.spring2.extend;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

/**
 * ZoomGrant 2020/3/19 23:40
 */
public class GRestHelper implements ApplicationContextAware {

    public Response relayRequest(String host, int port, HttpServletRequest request) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS)//设置写入超时时间
                .build();
        byte[] body = request.getInputStream().isReady() ? IOUtils.toByteArray(request.getInputStream()) : null;
        Request.Builder okBuilder = new Request.Builder();
        copyHeader(okBuilder, request);
        okBuilder.header("gt-time", System.currentTimeMillis() + "");
        okBuilder.url(copyParam(host, port, request));
        String method = request.getMethod();
        if (!"get".equalsIgnoreCase(method) && !ArrayUtils.isEmpty(body)) {
            okBuilder.method(request.getMethod(), RequestBody.create(body));
        }else if ("get".equalsIgnoreCase(method)){
            okBuilder.get();
        }
        return client.newCall(okBuilder.build()).execute();

    }

    public Response relayRequest(String host, HttpServletRequest request) throws IOException {
        return relayRequest(host, 8080, request);
    }

    public void copyHeader(Request.Builder reqBuilder, HttpServletRequest httpServletRequest){
        Enumeration<String> headers = httpServletRequest.getHeaderNames();
        while (headers.hasMoreElements()) {
            String key = headers.nextElement();
            if ("host".equalsIgnoreCase(key)) continue;
            if ("content-length".equalsIgnoreCase(key)) continue;
            if ("accept-encoding".equalsIgnoreCase(key)) continue;
            reqBuilder.addHeader(key, httpServletRequest.getHeader(key));
        }
    }

    public String copyParam(String host, int port,HttpServletRequest httpServletRequest) {
        Enumeration<String> params = httpServletRequest.getParameterNames();
        StringJoiner sb = new StringJoiner("&");
        while (params.hasMoreElements()) {
            String key = params.nextElement();
            sb.add(key + "=" + httpServletRequest.getParameter(key));
        }
        return host + ":" + port + httpServletRequest.getRequestURI() + "?" + sb.toString();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
