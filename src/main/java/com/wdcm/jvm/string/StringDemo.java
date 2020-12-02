package com.wdcm.jvm.string;

import java.util.concurrent.ConcurrentHashMap;

public class StringDemo {
    public static void main(String[] args) {
        String st1 = new StringBuilder("aa").append("bb").toString();
        System.out.println(st1);
        System.out.println(st1.intern());
        System.out.println(st1 == st1.intern());


        String st2 = new StringBuilder("ja").append("va").toString();
        System.out.println(st2);
        System.out.println(st2.intern());
        System.out.println(st2 == st2.intern());

        int week = 1;
        switch (week) {
            case 1:
                System.out.println("11111111");
                break;
            case 2:
                System.out.println("2222222222");
                break;
        }


        String weekl = "2";
        switch (weekl) {
            case "1":
                System.out.println("11111111");
                break;
            case "2":
                System.out.println("2222222222");
                break;
        }
        String s1="ab";
        String s2="a"+"b";
        String s3="a";
        String s4="b";
        String s5=s3+s4;
        System.out.println(s5==s2);
        System.out.println(s1==s2);

        int a = 1;
        float b = 2.1f;
//        b = (byte) (a + b);
        System.out.println(b);
//        b += a; // ok 
        a += b; // ok 

        System.out.println(a);

        long l = 10;

        System.out.println(l);

        new ConcurrentHashMap<>();
    }
}
