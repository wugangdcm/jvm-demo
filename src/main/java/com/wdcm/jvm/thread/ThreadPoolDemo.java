package com.wdcm.jvm.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        //查看cup个数
        System.out.println(Runtime.getRuntime().availableProcessors());
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 3, 0, null, null);
        Executors.newFixedThreadPool(5);
        Executors.newSingleThreadExecutor();
        Executors.newCachedThreadPool();
    }
}
