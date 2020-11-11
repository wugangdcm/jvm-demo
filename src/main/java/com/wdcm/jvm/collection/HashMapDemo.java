package com.wdcm.jvm.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * HashMap多线程不安全的验证 java.util.ConcurrentModificationException
 */
public class HashMapDemo {
    public static void main(String[] args) {

//        Hashtable hashtable = new Hashtable();
//        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
//        Collections.synchronizedMap(new HashMap<>());

        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 3001; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            }).start();
        }
    }
}
