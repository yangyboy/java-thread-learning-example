package com.mocc.thread.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceDemo {
    static AtomicStampedReference<Integer> money = new AtomicStampedReference<>(19, 0);

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            final int timestamp = money.getStamp();

            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        while (true) {
                            Integer reference = money.getReference();
                            if (reference < 20) {
                                if (money.compareAndSet(reference, reference + 20, timestamp, timestamp + 1)) {
                                    System.out.println("余额小于20元，充值成功，余额：" + money.getReference() + "元");
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                    }
                }
            }.start();
        }


        new Thread() {

            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    while (true){
                        int timestamp = money.getStamp();
                        Integer reference = money.getReference();
                        if (reference > 10) {
                            System.out.println("大于10元");
                            if (money.compareAndSet(reference, reference - 10, timestamp, timestamp + 1)) {
                                System.out.println("成功消费10元，余额：" + money.getReference());
                                break;
                            }
                        }else {
                            System.out.println("没有足够的金额");

                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }
}
