package com.wdcm.jvm.lock;

import java.util.concurrent.TimeUnit;

class MyService implements Runnable {
    private String lockA;
    private String lockB;

    public MyService(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }


    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + " 获取锁" + lockA + " 等待" + lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + " 获取锁" + lockB + " 等待" + lockA);
            }
        }
    }
}

/**
 * 死锁编码
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new MyService(lockA, lockB), "t1").start();
        new Thread(new MyService(lockB, lockA), "t2").start();
    }
}
