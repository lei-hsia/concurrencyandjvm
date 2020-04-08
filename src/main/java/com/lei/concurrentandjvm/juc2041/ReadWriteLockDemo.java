package com.lei.concurrentandjvm.juc2041;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) throws InterruptedException {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 写入数据" + key);
            TimeUnit.SECONDS.sleep(1);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+"\t 写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 读取数据" + key);
            TimeUnit.SECONDS.sleep(1);
            Object object = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t 读取完成" + object);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}

/*
    ReadWriteLock: a PAIR of associated locks, one for read-only operation, one for write.
    The read lock may be held simultaneously by multiple reader threads, so long as there are no writers.
    The write lock is exclusive.

    场景: 5个线程写入缓存，另外5个从缓存中读;

    本来，写的时候要有锁，读的时候不需要;
    但是: 读的时候也要加锁: 因为只有读读放行，而写的时候不能读，读的时候也不能写，i.e. 只是读读的时候可以同时，其他时候都是要互斥的，
         如果读的时候不加锁就可能出现读的时候来写了(写因为加锁了所以不可能来一个读线程), 所以读也要加锁，保证和"写"互斥，只是可以同时读

    都加锁之后，上面Cache就完备: 写写互斥，读写互斥，读读放行，每一个线程都不会被别的线程加塞
* */

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 1; i <= 5; i++) {
            final int tmpInt = i;
            new Thread(() -> {
                try {
                    myCache.put(tmpInt+"", tmpInt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int tmpInt = i;
            new Thread(() -> {
                myCache.get(tmpInt+"");
            }, String.valueOf(i)).start();
        }
    }
}
