package com.github.cooker;

import java.util.concurrent.Semaphore;

/**
 * grant
 * 6/5/2020 1:45 下午
 * 描述：
 */
public class DiningPhilosophers {
    Semaphore[] semaphores = new Semaphore[5];

    public DiningPhilosophers() {
        for (int i = 0; i < semaphores.length; i++) {
            semaphores[i] = new Semaphore(1);
        }
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        semaphores[philosopher].acquire();
        semaphores[(philosopher + 1) % 5].acquire();
        pickLeftFork.run();
        pickRightFork.run();
        eat.run();
        putLeftFork.run();
        putRightFork.run();
        semaphores[(philosopher + 1) % 5].release();
        semaphores[philosopher].release();
    }


    public static void main(String[] args) {

    }

}