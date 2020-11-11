package com.wdcm.jvm.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * ArrayList多线程环境不安全验证  java.util.ConcurrentModificationException
 */
public class ArrayListDemo {
    public static void main(String[] args) {

        List<String> arrayList = new ArrayList();
        //三种线程安全的实现 CopyOnWriteArrayList用的多
//        Vector vector = new Vector();
//        List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());
//        List<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 300; i++) {
            new Thread(() -> {
                arrayList.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(Thread.currentThread().getName() + " end list = " + arrayList);
            }).start();
        }
    }
}
