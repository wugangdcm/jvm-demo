package com.wdcm.jvm.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁的简单实例
 */
public class SpinlockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference();


    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " lock");
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " unlock");
        atomicReference.compareAndSet(thread, null);
    }

    public static void main(String[] args) {
        SpinlockDemo spinlockDemo = new SpinlockDemo();

        new Thread(() -> {
            spinlockDemo.lock();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinlockDemo.unlock();
        }, "t1").start();

        //保证t1线程先执行获取锁
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //t1线程未释放锁之前，t2 while (!atomicReference.compareAndSet(null, thread))这段逻辑就是一直为false,因为当前内存中是t1线程
        new Thread(() -> {
            spinlockDemo.lock();
            spinlockDemo.unlock();
        }, "t2").start();
    }
}
