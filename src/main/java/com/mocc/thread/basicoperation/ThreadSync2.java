package com.mocc.thread.basicoperation;

public class ThreadSync2 implements Runnable{

    static ThreadSync2 instance = new ThreadSync2();
    static int i = 0;

    public synchronized void increase(){
        i++;
    }
    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
             increase();
        }
    }


    public static void main(String[] args) throws InterruptedException {
//        Thread thread1 = new Thread(instance);
//        Thread thread2 = new Thread(instance);
//
//        thread1.start();
//        thread2.start();
//        thread1.join();
//        thread2.join();
//
//        System.out.println(i);


        /**
         * 错误的同步对象锁演示
         */
        Thread thread3 = new Thread(new ThreadSync2());
        Thread thread4 = new Thread(new ThreadSync2());

        thread3.start();
        thread4.start();
        thread3.join();
        thread4.join();

        System.out.println(i);
    }


}
