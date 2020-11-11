package com.wdcm.jvm.collection;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Set多线程不安全的验证  java.util.ConcurrentModificationException
 */
public class HashSetDemo {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
//        Set<String> set1 = Collections.synchronizedSet(new HashSet<>());
//        Set<String> set2 = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 3000; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(Thread.currentThread().getName() + " end list = " + set);
            }).start();
        }
    }
}
