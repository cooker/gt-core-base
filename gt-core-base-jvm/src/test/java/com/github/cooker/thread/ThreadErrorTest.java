package com.github.cooker.thread;

import org.junit.Test;

/**
 * grant
 * 6/5/2020 4:32 下午
 * 描述：
 */
public class ThreadErrorTest {

    @Test
    public void thread() throws InterruptedException {
        Thread th = new Thread(new ThreadError());
        th.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName());
                e.printStackTrace();
            }
        });
        th.start();

        th.join();
    }


    @Test
    public void threadGroup() throws InterruptedException {
        ThreadGroup threadGroup = new ThreadGroup("scz");
        Thread th = new Thread(threadGroup, new ThreadError());
        th.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getThreadGroup().getName());
            }
        });
        th.start();
        th.join();

    }
}
