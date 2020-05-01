package com.github.cooker;

import java.util.concurrent.Semaphore;

/**
 * grant
 * 25/4/2020 9:43 下午
 * 描述：
 */
public class H2O {
    public H2O() {

    }

    java.util.concurrent.Semaphore o = new Semaphore(1);

    java.util.concurrent.Semaphore h = new Semaphore(2);

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        h.acquire();
        releaseHydrogen.run();
        o.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        o.acquire(2);
        releaseOxygen.run();
        h.release(2);
    }



}



