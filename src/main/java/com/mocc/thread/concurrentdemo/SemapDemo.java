package com.mocc.thread.concurrentdemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author 杨洋
 * @ClassName SemapDemo.java
 * @Description 共享锁，允许指定数量的线程同时执行
 * @CreateTime 2020年05月22日 10:15:00
 */
public class SemapDemo implements Runnable {

    final Semaphore semp = new Semaphore(5);

    @Override
    public void run() {

        try {
            semp.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId()+"：done!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semp.release();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(20);
        final SemapDemo demo = new SemapDemo();
        for (int i = 0; i < 20; i++) {
            exec.submit(demo);
        }
    }
}
