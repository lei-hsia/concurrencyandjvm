package com.lei.concurrentandjvm.jmmjvmjucatomiclock20410;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo { // ABA问题的解决    AtomicStampedReference
    public static void main(String[] args) {
//        HashSet
//        CopyOnWriteArraySet
        //ReentrantLock
        AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100, (int)(System.currentTimeMillis()));

        System.out.println("------- 导致ABA问题 ---------");
        new Thread(() -> {
            System.out.println(atomicReference.compareAndSet(100, 101)+"\t"+atomicReference.get());
            System.out.println(atomicReference.compareAndSet(101, 100)+"\t"+atomicReference.get());
        }, "t1").start();

        new Thread(() -> {
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(atomicReference.compareAndSet(100, 2020)+"\t"+atomicReference.get());
        }, "t2").start();

        try { TimeUnit.SECONDS.sleep(4); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println("------- ABA问题解决 ---------");

        int stamp = atomicStampedReference.getStamp();
        new Thread(() -> {
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(atomicStampedReference.compareAndSet(100, 101,
                    stamp, stamp+1));
            System.out.println(atomicStampedReference.getStamp());
            System.out.println(atomicStampedReference.compareAndSet(101, 100,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println(atomicStampedReference.getStamp());
        }, "t3").start();

        new Thread(() -> {
            try { TimeUnit.SECONDS.sleep(7); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(atomicStampedReference.compareAndSet(100, 101,
                    stamp, stamp+1));
            System.out.println(atomicStampedReference.getStamp());
        }, "t4").start();
    }
}
