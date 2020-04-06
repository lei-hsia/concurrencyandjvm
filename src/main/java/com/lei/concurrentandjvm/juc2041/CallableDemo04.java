package com.lei.concurrentandjvm.juc2041;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("come in call method");
        return 1024;
    }
}

public class CallableDemo04 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> futureTask = new FutureTask(new MyThread()); // 实现了一个Callable

        new Thread(futureTask, "name").start(); // 本身也是Runnable的子类

        Integer result = futureTask.get();

        System.out.println(result);
    }
}
