package com.wdcm.jvm.concurrent.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列 生产一个消费一个 否则生产会阻塞。
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue blockingQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " start put A1");
                blockingQueue.put("A1");
                System.out.println(Thread.currentThread().getName() + " start put A2");
                blockingQueue.put("A2");
                System.out.println(Thread.currentThread().getName() + " start put A3");
                blockingQueue.put("A3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        //消费数据后停留几秒 方便观察
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getName() + "\t" + blockingQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2").start();

    }
}
