package com.wdcm.jvm.algorithm;

/**
 * 选择排序的逻辑
 * 1、从起始点出发，挨个与下一个元素比较，如果下一个小，就获取下一个的下标继续找 直到循环完数组找出最小的下标
 * 2、出发点和最小下标两个位置数据交换
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {5, 8, 4, 1, 9, 10};

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }


        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
