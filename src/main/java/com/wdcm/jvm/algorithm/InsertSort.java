package com.wdcm.jvm.algorithm;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {5, 4, 8, 2, 6, 1, 9, 3};
        sorts(arr);
        prints(arr);
    }

    private static void sorts(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
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
