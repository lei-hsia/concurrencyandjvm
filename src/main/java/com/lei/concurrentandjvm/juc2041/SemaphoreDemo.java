package com.lei.concurrentandjvm.juc2041;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/*
    Semaphore: 1. 控制并发数量(限流); 2. 资源互斥

    CRUD: 基本功; 高并发，多线程，微服务，分布式: GOOD
*/
public class SemaphoreDemo {
    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(3); // 实际上, 如果资源数为1，那么就是多线程争抢同一个资源

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t 抢占到了资源");
                    // 这里, 如果要求"拿到资源的线程只能拥有资源20秒, e.g. 那么这里改成20秒就行了"
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName()+"\t 释放掉了资源");
                }
            }, String.valueOf(i)).start();
        }
    }
}
