package com.mocc.thread.concurrentdemo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 杨洋
 * @ClassName ReentrantLockDemo.java
 * @Description
 * @CreateTime 2020年05月19日 19:38:00
 */
public class ReentrantLockDemo implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();//可重入
            try {
                i++;
            } finally {
                lock.unlock();
                lock.unlock();//重入后，重复退出
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new ReentrantLockDemo());
        Thread t2 = new Thread(new ReentrantLockDemo());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
