package com.wdcm.jvm.concurrent.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue blockingQueue = new ArrayBlockingQueue(10);
        //add(E) 元素满后 抛出异常 java.lang.IllegalStateException: Queue full
        //put(E) 元素满后 一直阻塞
        //offer(E) 元素满后 返回false,否则返回true
        //offer(E,1,TimeUnit.SECONDS) 在该队列的尾部插入指定的元素，等待指定的等待时间，以使空间在队列已满时变为可用。
        for (int i = 0; i < 5; i++) {
//            blockingQueue.add(i);
//            blockingQueue.add(i);
//            blockingQueue.put(i);
//            blockingQueue.offer(i);
            boolean result = blockingQueue.offer(i, 1, TimeUnit.SECONDS);
            System.out.println(result);
        }

        //blockingQueue.element() 检索但不删除由此deque表示的队列的头部（换句话说，该deque的第一个元素）。没有元素：java.util.NoSuchElementException
        //blockingQueue.peek() 检索但不删除由此deque表示的队列的头部（换句话说，该deque的第一个元素），如果此deque为空，则返回 null 。
//        System.out.println(blockingQueue.element());
//        System.out.println(blockingQueue.peek());

        //remove() 当队列没有数据时，报错 java.util.NoSuchElementException
        //take() 当队列没有数据时，一直阻塞
        //poll() 当队列没有数据时，返回null
        //poll(,TimeUnit.SECONDS) 检索并删除此队列的头，等待指定的等待时间（如有必要）使元素变为可用。
        for (int i = 0; i < 6; i++) {
//            blockingQueue.remove();
//            blockingQueue.take();
//            Object obj = blockingQueue.poll();
            Object obj = blockingQueue.poll(1, TimeUnit.SECONDS);
            System.out.println(obj);
        }
    }
}
