package com.wdcm.jvm.gc;

public class StringDemo {
    public static void main(String[] args) {
        String s3 = "abc";
        String s1 = new String("abc");
        String s2 = new String("abc");
        System.out.println(s1==s2);
        System.out.println(s1.equals(s2));
        System.out.println(s1==s3);
        System.out.println(s1.equals(s3));

        Integer a1 = 125;
        Integer a2 = 125;
        System.out.println(a1.equals(a2));

    }
}
