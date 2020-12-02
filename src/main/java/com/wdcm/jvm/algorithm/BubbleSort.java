package com.wdcm.jvm.algorithm;

/**
 * 冒泡排序的逻辑
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {5, 4, 8, 2, 6, 1, 9};

        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
