package com.mocc.thread.basicoperation;

public class TheradJoin {
    public static int i = 0;

    public static class AddThread extends Thread {
        @Override
        public void run() {
            for (i = 0; i < 10000000; i++) ;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AddThread at = new AddThread();
        at.start();
        at.join();//线程执行完毕后，系统会调用notifyAll()，不要在Thread实例上使用 wait()和notify()方法，因为会被jvm调用唤醒
        System.out.println(i);
    }
}
