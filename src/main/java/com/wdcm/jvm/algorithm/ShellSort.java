package com.wdcm.jvm.algorithm;

/**
 * 希尔排序 基于快速排序的改进版
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {5, 4, 8, 2, 6, 1, 9, 3};
        sorts(arr);
        prints(arr);
    }

    private static void sorts(int[] arr) {
        int h = 1;
        while (h < arr.length / 3) {
            h = h * 3 + 1;
        }
        for (int grap = h; grap > 0; grap = (grap - 1) / 3) {
            for (int i = grap; i < arr.length; i++) {
                for (int j = i; j > grap - 1; j -= grap) {
                    if (arr[j] < arr[j - 1]) {
                        swap(arr, j, j - 1);
                    }
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void prints(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}
