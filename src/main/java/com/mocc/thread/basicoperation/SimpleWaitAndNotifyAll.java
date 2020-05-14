package com.mocc.thread.basicoperation;

public class SimpleWaitAndNotifyAll {

    static Object object = new Object();

    public static class T1 extends Thread {
        public void run() {
            synchronized (object) {
                try {
                    System.out.println(System.currentTimeMillis() + ":T1 wait for object ");
                    object.wait();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ":T1 end!");
            }
        }
    }

    public static class T2 extends Thread {
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ":T2 start! notify all thread");
                object.notifyAll();
                System.out.println(System.currentTimeMillis() + ":T2 end!");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        T1 t1_1 = new T1();

        t1.start();
        t1_1.start();
//        Thread.sleep(1000);
        T2 t2 = new T2();
        t2.start();
    }
}
