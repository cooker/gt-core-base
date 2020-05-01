package com.github.cooker;


/**
 * grant
 * 25/4/2020 2:07 下午
 * 描述：
 */
public class FooBar {
    private int n;
    private volatile boolean isFoo;
    public FooBar(int n) {
        this.n = n;
        this.isFoo = true;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            // printFoo.run() outputs "foo". Do not change or remove this line.
            while (!isFoo){
                Thread.yield();
            }
            printFoo.run();
            isFoo = false;
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            while (isFoo){
                Thread.yield();
            }
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            isFoo = true;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        FooBar fooBar = new FooBar(5);
        long now = System.currentTimeMillis();
        Thread thread = new Thread(
                ()->{
                    try {
                        fooBar.foo(()->{
                            System.out.print("foo");
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );

        Thread thread1 = new Thread(
                ()->{
                    try {
                        fooBar.bar(()->{
                            System.out.print("bar");
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );

        thread.start();
        thread1.start();
        System.out.println();
        System.out.println(System.currentTimeMillis() - now);
    }
}
