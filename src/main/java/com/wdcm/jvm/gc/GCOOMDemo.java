package com.wdcm.jvm.gc;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.UUID;

/**
 * OOM 内存溢出的几种常见错误分析
 */
public class GCOOMDemo {
    public static void main(String[] args) {
//        taskOverflow();
        javaHeapSpace();
//        gcOverheadLimitExceeded();
//        directBufferMemory();
//        unableCreateNewNativeThread();
    }

    //Exception in thread "main" java.lang.StackOverflowError
    private static void taskOverflow() {
        taskOverflow();
    }

    //-Xms10m -Xmx10M
    //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
    private static void javaHeapSpace() {
        String str = "asasasasd";
        while (true) {
            str += str + UUID.randomUUID().toString().substring(0, 10) + UUID.randomUUID().toString().substring(12, 20);
            str.intern();
        }
    }

    /**
     * -Xms10m -Xmx10M -XX:+PrintGCDetails -XX:+PrintCommandLineFlags -XX:MaxDirectMemorySize=5M -XX:+UseG1GC
     * Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
     * GC回收时间过长会抛出该异常。过长的定义是超过98%的时间用来做GC并且回收了不到2%
     * 堆内存。连续多次GC都只回收了不到2%的极端情况下才会抛出。
     */
    private static void gcOverheadLimitExceeded() {
        Integer i = 0;
        ArrayList arrayList = new ArrayList();
        while (true) {
            arrayList.add(String.valueOf(++i).intern());
        }
    }

    /**
     * 本地初始内存大小=1799.5MB 大约内存的1/4
     * -Xms10m -Xmx10M -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5M
     * Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
     * 跟元空间Metasapce一样 用的是本地内存，不存在堆内存中
     * ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);
     */
    private static void directBufferMemory() {
        System.out.println("本地初始内存大小=" + sun.misc.VM.maxDirectMemory() / (double) 1024 / 1024 + "MB");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }

    /**
     * 一个应用进程创建太多线程 超过系统承载极限  Linux系统默认的是1024个 可以修改linux服务器配置 改大这个数量
     */
    private static void unableCreateNewNativeThread() {
        for (int i = 0; ; i++) {
            System.out.println("***************************************** i = " + i);
            new Thread(() -> {
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        }


    }
}
