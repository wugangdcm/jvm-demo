package com.wdcm.jvm.concurrent.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class ProdConsumer {
    private volatile boolean FLAG = true;
    private BlockingQueue<String> blockingQueue;
    private AtomicInteger atomicInteger = new AtomicInteger();

    public ProdConsumer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName() + "***********");
    }

    /**
     * 生产者生产数据 直到被某个时间打断
     */
    public void prod() throws InterruptedException {
        String data = null;
        boolean result = true;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            result = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            if (result) {
                System.out.println(Thread.currentThread().getName() + "****生产数据" + data + "****生产结果" + result);
            } else {
                System.out.println(Thread.currentThread().getName() + "****生产数据失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "****生产结束" + FLAG);
    }

    /**
     * 生产者生产数据 直到被某个时间打断
     */
    public void comsumer() throws InterruptedException {
        while (FLAG) {
            String data = blockingQueue.poll(2, TimeUnit.SECONDS);
            if (null == data) {
                System.out.println(Thread.currentThread().getName() + "****暂无消费数据，等待生产数据过来");
            } else {
                System.out.println(Thread.currentThread().getName() + "****消费数据" + data);
            }
        }
        System.out.println(Thread.currentThread().getName() + "****消费结束" + FLAG);
    }

    public void stop() {
        this.FLAG = false;
    }

}


public class ProducerConsumerBlockQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        ProdConsumer prodConsumer = new ProdConsumer(new ArrayBlockingQueue<String>(10));

        new Thread(() -> {
            try {
                prodConsumer.prod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();


        new Thread(() -> {
            try {
                prodConsumer.comsumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();


        TimeUnit.SECONDS.sleep(5);

        prodConsumer.stop();
    }
}
