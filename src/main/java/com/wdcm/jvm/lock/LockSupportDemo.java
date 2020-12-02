package com.wdcm.jvm.lock;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
    public static void main(String[] args) throws InterruptedException {

        Thread a = new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "\t" + " come in");
                LockSupport.park();
                System.out.println(Thread.currentThread().getName() + "\t" + " 被唤醒 1");
                Thread.sleep(5000);
                LockSupport.park();
                System.out.println(Thread.currentThread().getName() + "\t" + "被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A");
        a.start();

        Thread b = new Thread(() -> {
            LockSupport.unpark(a);
            System.out.println(Thread.currentThread().getName() + "\t" + "通知a");
        }, "B");
        b.start();

        Thread.sleep(3000);
        Thread c = new Thread(() -> {
            LockSupport.unpark(a);
            System.out.println(Thread.currentThread().getName() + "\t" + "通知a");
        }, "C");
        c.start();


    }
}
