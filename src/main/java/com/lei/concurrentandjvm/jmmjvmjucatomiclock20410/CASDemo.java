package com.lei.concurrentandjvm.jmmjvmjucatomiclock20410;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        // new ReentrantLock()

        System.out.println(atomicInteger.compareAndSet(5, 2020)+"\t atomicInteger is: "+atomicInteger);
        System.out.println(atomicInteger.compareAndSet(5, 1024)+"\t atomicInteger is: "+atomicInteger);
    }
}
