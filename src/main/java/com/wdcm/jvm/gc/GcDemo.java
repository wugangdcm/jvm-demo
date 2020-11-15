package com.wdcm.jvm.gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class GcDemo {
    public static void main(String[] args) throws InterruptedException {
        //-Xms1024M -Xmx1024M -XX:+PrintGCDetails
        System.out.println("********************");
        long cpu = Runtime.getRuntime().availableProcessors();
        long totalMemory = Runtime.getRuntime().totalMemory();
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("几核cpu =======" + cpu);
        System.out.println("初始内存 =======" + totalMemory);
        System.out.println("最大内存 =======" + maxMemory);

//        byte[] bytes = new byte[30 * 1024 * 1024];
//        Thread.sleep(Integer.MAX_VALUE);

        System.out.println("========================================================");
        //强引用
        Object o1 = new Object();
        Object o2 = o1;
        o1 = null;
        o2 = null;
        System.gc();
        System.out.println(o2);

        System.out.println("========================================================");
        //软引用
        Object o3 = new Object();
        SoftReference softReference = new SoftReference(o3);
        System.out.println(o3);
        System.out.println(softReference.get());
        o3 = null;
        System.gc();
        try {
//            byte[] bytes = new byte[30 * 1024 * 1024];
        } catch (Exception e) {

        } finally {
            System.out.println(o3);
            System.out.println(softReference.get());
        }
        System.out.println("========================================================");

        //弱引用
        Object o4 = new Object();
        WeakReference weakReference = new WeakReference(o4);
        System.out.println(o4);
        System.out.println(weakReference.get());
        o4 = null;
        System.gc();
        System.out.println(o4);
        System.out.println(weakReference.get());
        System.out.println("========================================================");

        Object o5 = new Object();
        ReferenceQueue referenceQueue = new ReferenceQueue();
        PhantomReference phantomReference = new PhantomReference(o5, referenceQueue);
        System.out.println(o5);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue);
        o5 = null;
        System.gc();
        System.out.println(o5);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue);

//        WeakHashMap weakHashMap = new WeakHashMap();
    }

}
