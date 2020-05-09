package com.github.cooker;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * grant
 * 6/5/2020 10:03 上午
 * 描述：
 * 如果这个数字可以被 3 整除，输出 "fizz"。
 * 如果这个数字可以被 5 整除，输出 "buzz"。
 * 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
 */
class FizzBuzz {
    // Initialize the permit, limit to one.
    private Semaphore sema = new Semaphore(1);

    // The current number.
    private int curNum = 1;

    private int n;

    public FizzBuzz(int n) {
        this.n = n;
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for (; ; ) {
            // Acquire the permit, try to run the logic exclusively.
            this.sema.acquire(1);
//            System.out.println("fizz");

            try {
                if (this.curNum > n) {
                    return;
                }

                if ((this.curNum % 3 == 0) && (this.curNum % 5 != 0)) {
                    printFizz.run();
                    this.curNum++;
                }
            } finally {
                // Release the permit anyway.
                this.sema.release(1);
            }
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (; ; ) {
            this.sema.acquire(1);
//            System.out.println("buzz");

            try {
                if (this.curNum > n) {
                    return;
                }

                if ((this.curNum % 3 != 0) && (this.curNum % 5 == 0)) {
                    printBuzz.run();
                    this.curNum++;
                }
            } finally {
                this.sema.release(1);
            }
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (; ; ) {
            this.sema.acquire(1);
//            System.out.println("fizzbuzz");

            try {
                if (this.curNum > n) {
                    return;
                }

                if ((this.curNum % 3 == 0) && (this.curNum % 5 == 0)) {
                    printFizzBuzz.run();
                    this.curNum++;
                }
            } finally {
                this.sema.release(1);
            }
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for (; ; ) {
            this.sema.acquire(1);
//            System.out.println("number");

            try {
                if (this.curNum > n) {
                    return;
                }

                if ((this.curNum % 3 != 0) && (this.curNum % 5 != 0)) {
                    printNumber.accept(this.curNum);
                    this.curNum++;
                }
            } finally {
                this.sema.release(1);
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        FizzBuzz fizzBuzz = new FizzBuzz(1);

        Thread a = new Thread(() -> {
            try {
                fizzBuzz.fizz(() -> {
                    System.out.print("fizz,");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread b = new Thread(() -> {
            try {
                fizzBuzz.buzz(() -> {
                    System.out.print("buzz,");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread c = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> {
                    System.out.print("fizzbuzz,");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread d = new Thread(() -> {
            try {
                fizzBuzz.number((x) -> {
                    System.out.print(x + ",");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        a.start();
        b.start();
        c.start();
        d.start();

        a.join();

    }

}
