package com.mocc.thread.concurrentdemo.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author 杨洋
 * @ClassName DeadlockChecker.java
 * @Description
 * @CreateTime 2020年05月19日 20:06:00
 */
public class DeadlockChecker {

    private final static ThreadMXBean mbean = ManagementFactory.getThreadMXBean();

    final static Runnable deadlockCheck = new Runnable() {
        @Override
        public void run() {
            for (;;){
                long[] deadlockedThreadIds = mbean.findDeadlockedThreads();
                if(deadlockedThreadIds != null){
                    ThreadInfo[] threadInfos = mbean.getThreadInfo(deadlockedThreadIds);

                    for (Thread t : Thread.getAllStackTraces().keySet()) {
                        for (int i = 0; i < threadInfos.length; i++) {
                            if(t.getId() == threadInfos[i].getThreadId()){
                                t.interrupt();
                            }
                            
                        }
                    }

                }

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {

                }

            }

        }
    };


    public static void check(){
        Thread t = new Thread(deadlockCheck);
        t.setDaemon(true);
        t.start();
    }
}
