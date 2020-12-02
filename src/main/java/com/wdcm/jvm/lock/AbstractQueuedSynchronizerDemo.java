package com.wdcm.jvm.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 抽象队列同步器
 */
public class AbstractQueuedSynchronizerDemo {

    public static void main(String[] args) throws InterruptedException {

        Lock lock = new ReentrantLock();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " come in");
                Thread.sleep(10 * 60 * 1000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "A").start();

        Thread.sleep(500);

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " come in");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "B").start();

        Thread.sleep(500);

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " come in");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "C").start();

    }
}
