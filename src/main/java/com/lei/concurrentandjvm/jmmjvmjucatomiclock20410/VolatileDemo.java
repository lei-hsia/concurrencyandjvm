package com.lei.concurrentandjvm.jmmjvmjucatomiclock20410;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyNumber {
    volatile int number = 0;

    public void changeTo10() {
        this.number = 10;
    }

    public void increment() {
        number++;
    }

    // addAndIncrement, addAndGet, getAndAdd, incrementAndAdd, etc. all methods pre-defined
    AtomicInteger atomicInteger = new AtomicInteger();

}


public class VolatileDemo {
    public static void main(String[] args) {

        MyNumber myNumber = new MyNumber();

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myNumber.increment();
                    myNumber.atomicInteger.getAndIncrement();
                }
            }, String.valueOf(i)).start();
        }

        // 等待上面20个线程都执行完, 再用main线程取得值看结果是多少
        while (Thread.activeCount() > 2) { // 2: main, gc
            Thread.yield(); // current thread willing to yield current use
        }

        System.out.println(Thread.currentThread().getName()+"\t final number: "+ myNumber.number);
        System.out.println(Thread.currentThread().getName()+"\t final atomic number: "+myNumber.atomicInteger);

        //seeIsOK(myNumber);
    }

    private static void seeIsOK(MyNumber myNumber) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t come in");
            try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
            myNumber.changeTo10();
        }, "AAA").start();

        // volatile保证了可见性, i.e. 一个线程修改值之后，其它的线程迅速获得该值的最新值通知
        while (myNumber.number == 0) {

        }

        System.out.println(Thread.currentThread().getName()+"\t finished");
    }
}
