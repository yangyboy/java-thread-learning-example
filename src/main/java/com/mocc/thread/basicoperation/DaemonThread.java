package com.mocc.thread.basicoperation;

public class DaemonThread extends Thread {

    @Override
    public void run() {
        for (;;){

        }
    }

    public static void main(String[] args) {
        Thread t = new DaemonThread();
        t.setDaemon(true);
        t.start();
    }
}
