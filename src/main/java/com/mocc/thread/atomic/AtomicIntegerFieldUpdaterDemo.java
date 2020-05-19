package com.mocc.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author 杨洋
 * @ClassName AtomicIntegerFieldUpdaterDemo.java
 * @Description
 * @CreateTime 2020年05月19日 17:50:00
 */
public class AtomicIntegerFieldUpdaterDemo {
    public static class Candidate{
        int id;
        volatile int score;//不使用volatile修饰，将抛出异常java.lang.IllegalArgumentException: Must be volatile typ
    }

    public final static AtomicIntegerFieldUpdater<Candidate> scoreUpdater = AtomicIntegerFieldUpdater.newUpdater(Candidate.class,"score");
    public static AtomicInteger allScore = new AtomicInteger(0);//验证updater是否正确

    public static void main(String[] args) throws InterruptedException {
        final  Candidate candidate = new Candidate();
        Thread[] ts = new Thread[10000];

        for (int i = 0; i < ts.length; i++) {
            ts[i] = new Thread(){
                @Override
                public void run() {
                    if(Math.random() > 0.4){
                        scoreUpdater.incrementAndGet(candidate);
                        allScore.incrementAndGet();

                    }
                }
            };
            ts[i].start();
        }
        for (int j = 0; j < 10000; j++) {
            ts[j].join();
        }

        System.out.println("score =" + candidate.score);
        System.out.println("allScore =" + allScore);
    }
}
