package com.wdcm.jvm.concurrent;

import com.wdcm.jvm.concurrent.enums.CountEnum;

import java.util.concurrent.CountDownLatch;

/**
 * 倒计时 所有线程都执行完之后 才执行主线程下的业务逻辑
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "开始处理业务逻辑。。。");
                countDownLatch.countDown();
            }, CountEnum.getNameByCode(i)).start();
        }
        countDownLatch.await();
        System.out.println("所有业务执行完成在执行下面业务。。。");

    }
}
