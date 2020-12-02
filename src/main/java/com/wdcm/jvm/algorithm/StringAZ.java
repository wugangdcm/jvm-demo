package com.wdcm.jvm.algorithm;

public class StringAZ {
    /**
     * 给定一个只由小写字母a-z组成的字符创str,返回其中最长无重复字符的子串长度
     *
     * @param args
     */
    public static void main(String[] args) {
//        String str = "abcdc";
        String str = "abcdcdefdghijk";

        System.out.println(lnrs(str));
    }

    private static int lnrs(String str) {
        char[] chars = str.toCharArray();
        int n = str.length();

        int[] last = new int[26];
        for (int i = 0; i < 26; i++) {
            last[i] = -1;
        }

        last[chars[0] - 'a'] = 0;

        int max = 1;
        int preMaxLen = 1;
        for (int i = 1; i < n; i++) {
            preMaxLen = Math.min(i - last[chars[i] - 'a'], preMaxLen + 1);
            max = Math.max(max, preMaxLen);

            last[chars[i] - 'a'] = i;
        }

        return max;
    }
}
