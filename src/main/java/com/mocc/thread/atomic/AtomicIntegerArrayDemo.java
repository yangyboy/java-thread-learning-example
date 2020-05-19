package com.mocc.thread.atomic;

import java.lang.invoke.VolatileCallSite;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author 杨洋
 * @ClassName AtomicIntegerArrayDemo.java
 * @Description
 * @CreateTime 2020年05月19日 16:13:00
 */
public class AtomicIntegerArrayDemo {
    static volatile AtomicIntegerArray array = new AtomicIntegerArray(10);

    public static class addThread implements Runnable {

        @Override
        public void run() {
            int index = 0;
            for (int i = 0; i < 100000; i++) {
                if(index >9){
                    index = 0;
                }
                    array.getAndIncrement(index);
                index ++ ;
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[10];
        for (int j = 0; j < 10; j++) {
            threads[j] = new Thread(new addThread());
        }

        for (int j = 0; j < 10; j++) {
            threads[j].start();
        }
        for (int j = 0; j < 10; j++) {
            threads[j].join();
        }

        System.out.println(array);
//          a  b  c
//        1/10=0.1

//        System.out.println(10047%10);
    }
}
