package com.wdcm.jvm.volidale;

/**
 * volatile指令重排验证
 */
public class VolatileSortDemo {
    private static volatile int a, b = 0;
    private static volatile int x, y = 0;


    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            Thread t1 = new Thread(() -> {
                a = 1;//1
                x = b;//2
            });


            Thread t2 = new Thread(() -> {
                b = 1;//3
                y = a;//4

            });
            t1.start();
            t2.start();

            t1.join();
            t2.join();
            //如果没有指令重排的操作 那么存在以下几种访问顺序
            //1->2->3->4 x=0 y=1
            //1->3->4->2 x=1 y=1
            //1->3->2->4 x=1 y=1

            //3->4->1->2 x=1 y=0
            //3->1->2->4 x=1 y=1
            //3->1->4->2 x=1 y=1

            //结果是不重排的前提下 那么xy总有一个为1  不可能存在 x=0 y=0 的情况
            //存在 即代表指令存在重排序
            if (x == 0 && y == 0) {
                System.out.println("当前次数" + i);
                break;
            }
        }
    }
}
