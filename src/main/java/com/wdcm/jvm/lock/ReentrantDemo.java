package com.wdcm.jvm.lock;

class MyReentrantLock {
    private boolean isLock = false;
    private int lockAccount = 0;
    private Thread lockby = null;

    public synchronized void lock() throws InterruptedException {
        //第一个线程进来时 会把isLock置为true,并加锁的对象保留下来
        //下一个线程来访问额时候 如果锁没释放，请当前线程不是上锁的线程 那么进入等待中 等待其它操作唤醒
        Thread thread = Thread.currentThread();
        while (isLock && thread != lockby) {
            wait();
        }
        isLock = true;
        lockby = thread;
        lockAccount++;
    }

    public synchronized void unlock() {
        //解锁的时候 判断当前线程是不是加锁的线程 否 不处理
        //是同一个线程时 加锁次数减1 当为0的时候 唤醒其它在等待的线程来处理
        Thread thread = Thread.currentThread();
        if (thread == lockby) {
            lockAccount--;
            if (lockAccount == 0) {
                isLock = false;
                lockby = null;
                notify();
            }
        }
    }
}

/**
 * 可重入锁的简单实例
 * 原理 同一个线程 获取同步方法中另一个同步方法，这个是允许的 不会造成阻塞
 */
public class ReentrantDemo {
    MyReentrantLock myReentrantLock = new MyReentrantLock();

    public void methodA() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " methodA start ");
        myReentrantLock.lock();
        System.out.println(Thread.currentThread().getName() + " methodA lock ");
        methodB();
        myReentrantLock.unlock();
        System.out.println(Thread.currentThread().getName() + " methodA unlock ");
    }

    public void methodB() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " methodB start ");
        myReentrantLock.lock();
        System.out.println(Thread.currentThread().getName() + " methodB lock ");
        myReentrantLock.unlock();
        System.out.println(Thread.currentThread().getName() + " methodB unlock ");
    }

    public static void main(String[] args) {
        ReentrantDemo reentrantDemo = new ReentrantDemo();

        new Thread(() -> {
            try {
                reentrantDemo.methodA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();


        new Thread(() -> {
            try {
                reentrantDemo.methodA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();

    }
}

