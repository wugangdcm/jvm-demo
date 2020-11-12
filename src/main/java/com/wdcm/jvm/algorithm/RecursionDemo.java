package com.wdcm.jvm.algorithm;

/**
 * 递归算法
 */
public class RecursionDemo {
    public static void main(String[] args) {
        int result = cal(8, 12);
        System.out.println(result);
        int result2 = calN(3, 2);
        System.out.println(result2);
    }


    private static int cal(int x, int y) {
        if (x % y == 0) {
            return y;
        }
        return cal(y, x % y);
    }


    private static int calN(int x, int y) {
        if (x == 1) {
            return y;
        }
        return calN(x - 1, x * y);
    }
}
