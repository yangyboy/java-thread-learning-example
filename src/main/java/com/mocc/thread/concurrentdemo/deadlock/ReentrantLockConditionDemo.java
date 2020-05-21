package com.mocc.thread.concurrentdemo.deadlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 杨洋
 * @ClassName ReentrantLockDemo.java
 * @Description  ReentrantLock 特点：
 *               1.可重入
 *               2.可中断
 *               3.可限时
 *               4.公平锁
 * @CreateTime 2020年05月19日 19:38:00
 */
public class ReentrantLockConditionDemo implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();

    public static Condition condition = lock.newCondition();

    @Override
    public void run() {
            try {
                lock.lock();
                condition.await();
                System.out.println("thread is going on");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockConditionDemo reentrantLockConditionDemo = new ReentrantLockConditionDemo();

        Thread t1 = new Thread(reentrantLockConditionDemo);
        t1.start();
        Thread.sleep(2000);
        lock.lock();
        condition.signal();
        Thread.sleep(2000);
        lock.unlock();
    }
}
