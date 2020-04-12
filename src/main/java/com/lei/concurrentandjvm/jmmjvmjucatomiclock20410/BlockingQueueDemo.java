package com.lei.concurrentandjvm.jmmjvmjucatomiclock20410;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/*
        add/remove/element: 一般
        offer/poll/peek: offer > false, poll > null
        put/take: 一直阻塞
        offer+timeout/poll+timeout
* */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("x"));
        System.out.println();
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        // put,take: 死占，不见不散
        /*blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        blockingQueue.put("x");
        System.out.println();
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();*/

        System.out.println(blockingQueue.offer("a", 1, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b", 1, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c", 1, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("x", 2, TimeUnit.SECONDS)); //false
        System.out.println();
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());//null

    }
}
