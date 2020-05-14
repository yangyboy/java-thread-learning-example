package com.mocc.thread.basicoperation;

public class ThreadSync3 implements Runnable{

    static ThreadSync3 instance = new ThreadSync3();
    static int i = 0;

    public static synchronized void increase(){
        i++;
    }
    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
             increase();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        Thread thread3 = new Thread(new ThreadSync3());
        Thread thread4 = new Thread(new ThreadSync3());

        thread3.start();
        thread4.start();
        thread3.join();
        thread4.join();

        System.out.println(i);
    }


}
