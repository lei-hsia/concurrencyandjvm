package com.lei.concurrentandjvm.jvmgcjmm2044;

public class T {
    public static void main(String[] args) {
//        Thread t = new Thread();
//        t.start();
//        t.start(); //Exception in thread "main" java.lang.IllegalThreadStateException

        // Runtime: Runtime Data Area: java抽象成了一个对象:就是这个类
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
