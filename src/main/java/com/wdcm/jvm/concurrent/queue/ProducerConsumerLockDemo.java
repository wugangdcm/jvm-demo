package com.wdcm.jvm.concurrent.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 操作一个数据 初始值0 一个线程执行加1操作 一个线程执行减1操作，来回执行5次
 */

class OptData {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    /**
     * 生产时 number等于0才执行并唤醒其它线程 否则等待
     */
    public void add() {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            number++;
            condition.signal();
            System.out.println(Thread.currentThread().getName() + "\t" + number);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 消费时 等于0就等待 否则减1并唤醒其它线程
     */
    public void sub() {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            number--;
            condition.signal();
            System.out.println(Thread.currentThread().getName() + "\t" + number);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ProducerConsumerLockDemo {
    public static void main(String[] args) {
        OptData optData = new OptData();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                optData.add();
            }
        }, "t1").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                optData.sub();
            }
        }, "t2").start();
    }
}
