package com.lei.concurrentandjvm.juc2041;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/*  thread not safe:
    1 故障现象
            ConcurrentModificationException
    2. 导致原因
            多线程并发的情况下争抢同一个资源且没加锁
    3. 解决方法
        3.1 Vector
        3.2 Collections.synchronizedList(new ArrayList<>())
        3.3 CopyOnWriteArrayList
    4. 优化建议
* */
public class NotThreadSafeDemo03 {

    public static void main(String[] args) {
//        listNotSafe();
//        setNotSafe();
        mapNotSafe();

    }

    private static void mapNotSafe() {
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }

    private static void setNotSafe() {
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    private static void listNotSafe() {
        List<String> list = new CopyOnWriteArrayList<>();//Collections.synchronizedList(new ArrayList<>());// new Vector<>();//new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
