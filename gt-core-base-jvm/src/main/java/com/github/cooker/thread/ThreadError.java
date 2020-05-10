package com.github.cooker.thread;

/**
 * grant
 * 6/5/2020 4:31 下午
 * 描述：
 */
public class ThreadError implements Runnable{

    @Override
    public void run() {
        throw new NullPointerException("空");
    }
}
