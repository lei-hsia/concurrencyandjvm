package com.lei.concurrentandjvm.jmmjvmjucatomiclock20410;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

class MyResources {

    private volatile boolean FLAG = true; // volatile: 生产者消费者线程改变FLAG的状态要被对方可见
    private BlockingQueue<String> blockingQueue = null;
    private AtomicInteger atomicInteger = new AtomicInteger();

    // 设计思想: 传接口。因为并不知道会用哪一个阻塞队列，所以参数类型是接口，真实传递就传递接口的实现类
    public MyResources(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName()); // 传递参数往上到接口, 查看具体类型往下落实到实现类
    }

    public void myProd() throws InterruptedException {
        String data = null;
        boolean result;
        while (FLAG) {
            data = atomicInteger.incrementAndGet()+"";
            result = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            if (result) {
                System.out.println(Thread.currentThread().getName()+"\t 加入队列" +data);
            } else {
                System.out.println(Thread.currentThread().getName()+"\t 加入队列失败");
            }
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        System.out.println("PRODUCER: EXIT");
    }

    public void myConsume() throws InterruptedException {
        String data = null;
        while (FLAG) {
            data = blockingQueue.poll(2, TimeUnit.SECONDS);
            if (data != null) {
                System.out.println(Thread.currentThread().getName()+"\t 从队列取出"+data+"成功");
            } else {
                System.out.println(Thread.currentThread().getName()+"\t 取出队列失败");
            }
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        System.out.println("CONSUMER: NOTHING FOR 2s, EXIT");
    }

    public void stop() {
        this.FLAG = false;
        System.out.println("FORCE EXIT: FLAG = FALSE");
    }

}


public class ProdConsumer_BlockingQueueDemo {
    public static void main(String[] args) {

        MyResources myResources = new MyResources(new ArrayBlockingQueue<>(10));

        // 创建一个thread(Runnable, threadName): 如果资源类本身实现了Runnable,就不用用lambda表达式了，直接创建一个资源类对象就行了
        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+"\t 生产线程启动");
                myResources.myProd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName()+"\t 消费线程启动");
                myResources.myConsume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BBB").start();

        System.out.println();
        System.out.println();

        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("5秒钟时间到，叫停生产者消费者模型");
        System.out.println();
        myResources.stop();
    }
}
