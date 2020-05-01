package com.github.cooker;


import java.util.function.IntConsumer;

/**
 * grant
 * 25/4/2020 7:52 下午
 * 描述：
 */
public class ZeroEvenOdd {
    private int n;
    private int num = -1;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            synchronized (this) {
                if (num != -1) {
                    this.wait();
                }
                printNumber.accept(0);
                this.notifyAll();
                num = i;
            }
        }

    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int j = 2; j <= n; j = j + 2) {
            synchronized (this) {
                while (num % 2 == 1 || num == -1) {
                    this.wait();
                }
                printNumber.accept(j);
                this.notifyAll();
                num = -1;
            }
        }

    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int j = 1; j <= n; j = j + 2) {
            synchronized (this) {
                while (num % 2 == 2 || num == -1) {
                    this.wait();
                }
                printNumber.accept(j);
                this.notifyAll();
                num = -1;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(5);
        IntConsumer run = System.out::print;
        long now = System.currentTimeMillis();
        Thread t1 = new Thread(()-> {
            try {
                zeroEvenOdd.zero(run);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(()-> {
            try {
                    zeroEvenOdd.even(run);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(()-> {
            try {
                    zeroEvenOdd.odd(run);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(1000L);
        long end = System.currentTimeMillis();
        System.out.println();
        System.out.println(end - now);
    }
}
