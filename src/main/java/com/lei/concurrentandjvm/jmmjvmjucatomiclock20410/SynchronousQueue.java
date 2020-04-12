package com.lei.concurrentandjvm.jmmjvmjucatomiclock20410;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/*
*   * A {@code SynchronousQueue} has no internal capacity.
*   生产一个，消费一个
* */
public class SynchronousQueue {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new java.util.concurrent.SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+"\t put in 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"\t put in 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"\t put in 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();

        new Thread(() -> {
            try {
                try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName()+ "\t" + blockingQueue.take());
                try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName()+ "\t" + blockingQueue.take());
                try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName()+ "\t" + blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BBB").start();
    }
}
