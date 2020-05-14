package com.mocc.thread.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceDemo {

    public static final AtomicReference<String> atomicStr = new AtomicReference<>("abc");

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(){
                @Override
                public void run() {
                    try {
                        Thread.sleep((int) Math.abs(Math.random() *100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(atomicStr.compareAndSet("abc","def")){
                        System.out.println(Thread.currentThread().getName() + ":change value to def");
                    }else {
                        System.out.println(Thread.currentThread().getName() + ":change failed");
                    }
                }
            }.start();
        }

    }
}
