package com.wdcm.jvm.lock;

/**
 * 不可重入锁的简单实例
 */
class MyLock {
    boolean isLock = false;

    public synchronized void lock() throws InterruptedException {
        while (isLock) {
            wait();
        }
        isLock = true;
    }

    public synchronized void unlock() {
        isLock = false;
        notify();
    }
}

public class NonReentrantDemo {

    MyLock nonReentrant = new MyLock();

    public void methodA() throws InterruptedException {
        System.out.println("methodA start ");
        nonReentrant.lock();
        System.out.println("methodA lock ");
        methodB();
        nonReentrant.unlock();
        System.out.println("methodA unlock ");
    }

    public void methodB() throws InterruptedException {
        System.out.println("methodB start ");
        nonReentrant.lock();
        System.out.println("methodB lock ");
        nonReentrant.unlock();
        System.out.println("methodB unlock ");
    }

    public static void main(String[] args) throws InterruptedException {
        NonReentrantDemo nonReentrantDemo = new NonReentrantDemo();
        nonReentrantDemo.methodA();
    }
}
