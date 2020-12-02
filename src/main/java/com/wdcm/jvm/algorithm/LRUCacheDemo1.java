package com.wdcm.jvm.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheDemo1 extends LinkedHashMap {
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    static final Integer DEFAULT_CAPACITY = 16;
    private Integer initCapacity;

    public LRUCacheDemo1() {
        this(DEFAULT_CAPACITY);
    }

    public LRUCacheDemo1(Integer capacity) {
        //true 才会真正按使用的顺序进行重新排序 最近使用的移到列表尾部 实现最近最少使用淘汰策略
        //false 使用的是插入顺序 第一位删除
//        super(capacity, DEFAULT_LOAD_FACTOR, false);
        super(capacity, DEFAULT_LOAD_FACTOR, true);
        this.initCapacity = capacity;
    }

    /**
     * 列表长度大于定义的初始容量的时候执行淘汰策略
     *
     * @param eldest
     * @return
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return super.size() > initCapacity;
    }

    public static void main(String[] args) {
        LRUCacheDemo1 lruCache = new LRUCacheDemo1(3);
        lruCache.put(2, 2);
        lruCache.put(1, 1);
        System.out.println(lruCache.keySet());
        lruCache.get(1);
        lruCache.put(3, 3);
        System.out.println(lruCache.keySet());
        lruCache.put(4, 4);
        System.out.println(lruCache.keySet());
        lruCache.get(3);
        System.out.println(lruCache.keySet());
        lruCache.get(3);
        System.out.println(lruCache.keySet());
        lruCache.put(5, 5);
        System.out.println(lruCache.keySet());
        lruCache.put(6, 6);
        System.out.println(lruCache.keySet());
    }
}
