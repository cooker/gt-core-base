package com.github.cooker.listener;


import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import java.io.IOException;

/**
 * grant
 * 26/4/2020 9:41 下午
 * 描述：
 */
public class MyListener implements AsyncListener {
    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        System.out.println("onComplete");
    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
        System.out.println("onTimeout");
    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
        System.out.println("onError");
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
        System.out.println("onStartAsync");
    }
}
