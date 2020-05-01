package com.github.cooker.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/**
 * grant
 * 26/4/2020 11:06 下午
 * 描述：
 */
@Slf4j
@WebServlet(value = "/getway", name = "myServlet", asyncSupported = true)
public class MyServlet extends HttpServlet {

    @SneakyThrows
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addIntHeader("aaa", 1);
        resp.addDateHeader("aaa", new Date().getTime());
        Cookie cookie = new Cookie("sasa", "saas");
        cookie.setHttpOnly(true);
        resp.addCookie(cookie);
        log.info("buffer size={}", resp.getBufferSize());
        log.info("request Id:{}", req);
        resp.getOutputStream().println("aaaaaaa");
//        resp.getOutputStream().flush();
//        resp.getWriter().write("ssss");
        this.getServletContext().getRequestDispatcher("/static/useraa.html").include(req, resp);

//        resp.sendRedirect("/useraa");


//        req.getRequestDispatcher("/index.html").forward(req, resp);

//        AsyncContext asyncContext = req.startAsync(req, resp);
//        asyncContext.setTimeout(1000);
//        asyncContext.addListener(new AsyncListener() {
//            @Override
//            public void onComplete(AsyncEvent event) throws IOException {
//                System.out.println("onComplete");
//            }
//
//            @Override
//            public void onTimeout(AsyncEvent event) throws IOException {
//                System.out.println("onTimeout");
//            }
//
//            @Override
//            public void onError(AsyncEvent event) throws IOException {
//                System.out.println("onError");
//            }
//
//            @Override
//            public void onStartAsync(AsyncEvent event) throws IOException {
//
//            }
//        });
//        ServletResponse response = asyncContext.getResponse();
//        ServletOutputStream outputStream = response.getOutputStream();
//                outputStream
//                .setWriteListener(new WriteListener() {
//            @Override
//            public void onWritePossible() throws IOException {
//                System.out.println("onWritePossible");
//            }
//
//            @Override
//            public void onError(Throwable throwable) {
//                System.out.println("onError");
//            }
//        });
//        Thread.sleep(3000L);



//        asyncContext.dispatch("/user");
//        asyncContext.complete();
//        outputStream.println("ssssssss");
//        super.service(req, resp);

    }

}
