package com.mocc.thread.concurrentdemo.deadlock;

/**
 * @author 杨洋
 * @ClassName SynchronizedReentrantTest.java
 * @Description
 * @CreateTime 2020年05月19日 20:15:00
 */
public class SynchronizedReentrantTest extends SuperSynchronizedReentrantTest {

    public synchronized void doSomething() {
        System.out.println("child.doSomething(),current thread name is :" + Thread.currentThread().getName());
        doAnotherThing(); // 调用自己类中其他的synchronized方法
    }

    private synchronized void doAnotherThing() {
        super.doSomething(); // 调用父类的synchronized方法
        System.out.println("child.doAnotherThing(),current thread name is :" + Thread.currentThread().getName());
    }


    public static void main(String[] args) {
        SynchronizedReentrantTest child = new SynchronizedReentrantTest();
        child.doSomething();
    }

}

class SuperSynchronizedReentrantTest {
    public synchronized void doSomething() {
        System.out.println("father.doSomething(),current thread name is :" + Thread.currentThread().getName());
    }
}
