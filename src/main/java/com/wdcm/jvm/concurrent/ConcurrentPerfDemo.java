package com.wdcm.jvm.concurrent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 测试多线程下各种安全Map的性能 结果 ConcurrentHashMap高并发下性能最佳
 */
public class ConcurrentPerfDemo {

    public static void main(String[] args) throws InterruptedException {
        perf(new Hashtable(), 5);
        perf(Collections.synchronizedMap(new HashMap<>()), 5);
        perf(new ConcurrentHashMap(), 5);
    }

    private static void perf(Map map, int threshold) throws InterruptedException {
        System.out.println("perf cur map ================== " + map.getClass());
        long totaltime = 0L;
        final int MAX_ACCOUT = 5000000;
        for (int i = 0; i < 5; i++) {
            long starttime = System.nanoTime();
            ExecutorService executorService = Executors.newFixedThreadPool(threshold);

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < MAX_ACCOUT; j++) {
                        map.get(j);
                        map.put("j", j);
                    }
                }
            });
            executorService.shutdown();
            executorService.awaitTermination(2, TimeUnit.HOURS);
            long endtime = System.nanoTime();
            long time = (endtime - starttime) / 1000000L;
            System.out.println("time ======" + time + "ms");
            totaltime += time;

        }
        System.out.println("perf cur map ================== " + map.getClass() + " avg time ======" + (totaltime / 5) + "ms");
    }
}
