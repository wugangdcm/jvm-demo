package com.wdcm.jvm.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数据和一个目标数字 请在数组中找出两个数和为目标值的两个数的下标
 * 数组中同一个元素不能使用两次
 * 给定nums=[2,5,7,15] target = 9
 * <p>
 * <p>
 * 给定一个数m 求大于该数的最小n次幂 返回n
 */
public class TowSumDemo {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 5, 7, 15};
        int target = 9;

//        int[] result = sum1(nums, target);
        int[] result = sum2(nums, target);

        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
        int m = 31;
        int n = cal3(m);
        System.out.println(n);
    }

    private static int cal3(int m) {
        int n = m - 1;
        n |= n >>> 1;
        return n + 1;
    }

    private static int[] sum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int value = target - nums[i];
            if (map.containsKey(value)) {
                return new int[]{map.get(value), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }


    private static int[] sum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (target - nums[i] == nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
