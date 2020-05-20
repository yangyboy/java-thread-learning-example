package com.mocc.thread.concurrentdemo;

import com.mocc.thread.concurrentdemo.deadlock.DeadlockChecker;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 杨洋
 * @ClassName ReentrantLockInterruptedDemo.java
 * @Description 可重入锁死锁演示,并演示通过死锁检测中断死活线程
 * @CreateTime 2020年05月19日 19:53:00
 */
public class ReentrantLockInterruptedDemo implements Runnable {
    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;


    public ReentrantLockInterruptedDemo(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if(lock ==1 ){
                lock1.lockInterruptibly();
                Thread.sleep(500);
                lock2.lockInterruptibly();
            }else{
                lock2.lockInterruptibly();
                Thread.sleep(500);
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
//            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread())
                lock1.unlock();
            if (lock2.isHeldByCurrentThread())
                lock2.unlock();

            System.out.println(Thread.currentThread().getId() + ":线程退出");
        }


    }


    public static void main(String[] args) throws InterruptedException {
        ReentrantLockInterruptedDemo r1 = new ReentrantLockInterruptedDemo(1);
        ReentrantLockInterruptedDemo r2 = new ReentrantLockInterruptedDemo(2);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        //检查死锁，发现死锁后，中断掉死锁线程
        DeadlockChecker.check();

    }
}
