package com.mocc.thread.concurrentdemo;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 杨洋
 * @ClassName CountDdownLatchDemo.java
 * @Description
 * @CreateTime 2020年05月22日 14:43:00
 */
public class CountDownLatchDemo implements Runnable {
    static final CountDownLatch latch = new CountDownLatch(10);

    static final CountDownLatchDemo demo = new CountDownLatchDemo();

    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(10)*1000);
            System.out.println(Thread.currentThread().getId() + ":check complete");
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            exec.submit(demo);
        }
        latch.await();
        System.out.println("fire!");

        exec.shutdown();

    }
}
