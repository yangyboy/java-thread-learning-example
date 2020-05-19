package com.mocc.thread.concurrentdemo.deadlock;

/**
 * @author 杨洋
 * @ClassName DeadlockChecker.java
 * @Description
 * @CreateTime 2020年05月19日 20:06:00
 */
public class DeadlockChecker {


    public static void check(){
        Thread t = new Thread();
        t.setDaemon(true);
        t.start();
    }
}
