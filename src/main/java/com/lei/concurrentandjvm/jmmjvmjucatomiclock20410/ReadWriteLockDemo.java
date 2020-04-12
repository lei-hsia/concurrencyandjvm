package com.lei.concurrentandjvm.jmmjvmjucatomiclock20410;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
*   实际上缓存都需要三种使用方式: put, get, 清空(evict)
* */
class MyCache {
    // 凡是缓存: 都需要volatile, 因为缓存非常快，所以高并发下多线程修改主内存中的值，一旦修改就需要全部线程感知: 可见性非常重要
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 开始写入");
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            map.put(key,value);
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName()+"\t 写入完成");
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 开始读取");
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            Object object = map.get(key);
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName()+"\t 读取完成"+object);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}

/*
      小总结: 写只能独占，读可以同时多个；
          写: 原子+独占;
          读: 原子，不需要独占
* */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 1; i <= 5; i++) {
            final int intTmp = i;
            new Thread(() -> { // lambda表达式中基本类型只能用final
                myCache.put(intTmp+"",intTmp+"");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int intTmp = i;
            new Thread(() -> { // lambda表达式中基本类型只能用final
                myCache.get(intTmp+"");
            }, String.valueOf(i)).start();
        }
    }
}
