package com.mocc.thread.basicoperation;

/**
 * 线程中断演示
 */
public class ThreadInterrupt implements Runnable{

    @Override
    public void run() {
        while(true){
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Interruted!");
                break;
            }

            try {
                /**
                 * 线程sleep时，中断该线程时将会抛出异常，异常会清除中断标记位，需要在catch块中显示再次调用中断
                 */
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println("Interruted When Sleep");
                //设置中断状态，抛出异常后会清除中断标记位
                Thread.currentThread().interrupt();
            }
            Thread.yield();
        }

    }

    public static void main(String[] args) {
        Thread thread = new Thread(new ThreadInterrupt());
        thread.start();

        thread.interrupt();
    }

}
