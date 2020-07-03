package com.lei.concurrentandjvm.threadlocal;

import java.util.concurrent.atomic.AtomicInteger;

/*
*   synchronized: 多个线程，一个变量，其它线程等待，费时间
*   ThreadLocal:  多个线程，多个变量副本，每个线程有自己的变量副本，空间换时间
*       每个线程都有一个ThreadLocalMap的实例对象，并且通过ThreadLocal管理ThreadLocalMap
*
* ThreadLocal:
* * <p>Each thread holds an implicit reference to its copy of a thread-local
 * variable as long as the thread is alive and the {@code ThreadLocal}
 * instance is accessible; after a thread goes away, all of its copies of
 * thread-local instances are subject to garbage collection (unless other
 * references to these copies exist).
* */
public class ThreadLocalId {

    private static final AtomicInteger nextId = new AtomicInteger(0);

    private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>(){
        protected Integer initialValue() {
            return nextId.getAndIncrement();
        }
    };

    public static int get() {
        return threadId.get();
    }

    public static void remove() {
        threadId.remove();
    }
}
