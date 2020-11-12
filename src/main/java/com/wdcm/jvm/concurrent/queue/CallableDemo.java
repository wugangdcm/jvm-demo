package com.wdcm.jvm.concurrent.queue;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyResource implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t" + "call");
        return 100;
    }
}


class MyResourceRun implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "\t" + "run");
    }
}


public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyResource myResource = new MyResource();
        FutureTask<Integer> futureTask = new FutureTask<>(myResource);
        Thread thread = new Thread(futureTask, "t1");
        thread.start();
        //同样的task任务不会执行两次 会复用
//        Thread thread2 = new Thread(futureTask, "t1");
//        thread2.start();

        System.out.println(Thread.currentThread().getName() + "----------------");
        int num = 22;

        //线程是否执行完成的判断
//        while (!futureTask.isDone()){
//
//        }
        //get方法时 如果线程中方法没执行完成 会一致阻塞直到完成
        int result = futureTask.get();
        System.out.println("cal result ==================" + (num + result));


        MyResourceRun myResourceRun = new MyResourceRun();
        FutureTask futureTask2 = new FutureTask(myResourceRun, 1);
        Thread thread2 = new Thread(futureTask2, "t2");
        thread2.start();
        Object result2 = futureTask2.get();
        System.out.println("cal result ==================" + (result2));

    }
}
