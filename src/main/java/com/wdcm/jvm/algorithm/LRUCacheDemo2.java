package com.wdcm.jvm.algorithm;

import java.util.HashMap;
import java.util.Map;

public class LRUCacheDemo2 {

    class Node<K, V> {
        K key;
        V value;
        Node prev;//前节点
        Node next;//后一个节点

        public Node() {
            this.prev = this.next = null;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.prev = this.next = null;
        }
    }

    class DoubleListNode<K, V> {
        Node head;
        Node tail;

        //AQS原理一样 创建一个双向队列 head指向tail  tail指向head
        public DoubleListNode() {
                head = new Node();
                tail = new Node();
                 head.next = tail;
                tail.prev = head;
        }

        /**
         * 往头部添加节点从开始的 head tail 到 head B tail 到 head A B tail 下面模拟插入A节点时的情况
         * 当前节点的下一节点指向头节点的下一个节点  即 A---->B
         * 当前节点的上一节点指向头节点   即  Head<----A
         * 头节点的上一节点的下一节点指向当前节点   即 A<----B
         * 头节点指向当前节点    即 head---->A
         *
         * @param node
         */
        public void addHead(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        /**
         * 移除节点 加入当前数据为 head A B  C tail 此时B有用到 那么当前B需要先移除 然后插入头部 此时删除操作的移动情况
         * B的下一节点 C 指向 B 的上一节点A  即 A<---------C
         * B的上一节点 A 的下一节点指向 B 的下一节点C 即 A-------->C
         * B的前后指针清空 等待垃圾回收
         *
         * @param node
         */
        public void remove(Node node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
            node.next = null;
            node.prev = null;
        }

        /**
         * 这个就是最近最少使用的节点 满容量时需要淘汰的数据
         *
         * @return
         */
        public Node getLast() {
            return tail.prev;
        }
    }

    //初始容量大小
    private int capacity;
    //采用map节构存储数据
    Map<Integer, Node<Integer, Integer>> map;
    //双向列表
    DoubleListNode<Integer, Integer> doubleListNode;


    public LRUCacheDemo2(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        doubleListNode = new DoubleListNode<>();
    }

    /**
     * @param key
     * @return
     */
    public int get(Integer key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        Node<Integer, Integer> node = map.get(key);
        doubleListNode.remove(node);
        doubleListNode.addHead(node);
        return node.value;
    }

    /**
     * 新增时候判断是否已经存在 存在就覆盖值，然后从双向列表移除该节点，在把头节点的引用指向当前节点
     * 不存在 如果还没达到容量的阈值，
     * 1、放入map中，并加入双向列表，即把头节点的引用指向当前节点
     * 达到阈值时
     * 2、map容器中删除key，双向列表中移除尾节点的上一节点，然后执行 1 的逻辑
     *
     * @param key
     * @param value
     */
    public void put(Integer key, Integer value) {
        if (map.containsKey(key)) {
            Node<Integer, Integer> oldNode = map.get(key);
            oldNode.value = value;
            map.put(key, oldNode);

            doubleListNode.remove(oldNode);
            doubleListNode.addHead(oldNode);
        } else {
            if (map.size() == capacity) {
                Node nodeLast = doubleListNode.getLast();
                map.remove(nodeLast.key);
                doubleListNode.remove(nodeLast);
            }

            Node<Integer, Integer> newNode = new Node(key, value);
            map.put(key, newNode);
            doubleListNode.addHead(newNode);

        }
    }

    public static void main(String[] args) {
        LRUCacheDemo2 lruCache = new LRUCacheDemo2(3);
        lruCache.put(2, 2);
        lruCache.put(1, 1);
        System.out.println(lruCache.map.keySet());
        lruCache.get(1);
        System.out.println(lruCache.map.keySet());
        lruCache.put(3, 3);
        System.out.println(lruCache.map.keySet());
        lruCache.put(4, 4);
        System.out.println(lruCache.map.keySet());

        lruCache.put(5, 5);
        System.out.println(lruCache.map.keySet());
        lruCache.get(3);
        lruCache.put(6, 6);
        System.out.println(lruCache.map.keySet());
    }
}
