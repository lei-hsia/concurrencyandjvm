package com.lei.concurrentandjvm.jmmjvmjucatomiclock20410;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/*  实际上写法跟CAS中的while循环和compareAndSwap类似

    spinlock: 是指尝试获得锁的线程不会立即阻塞，而是采用循环的方式获得锁；减少上下文的切换,不阻塞，但是循环消耗CPU
* */
public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>(); // 原子引用初始值指向的线程是null

    public void myLock() {
        System.out.println(Thread.currentThread().getName()+"\t come in <<<<<< ");
        Thread thread = Thread.currentThread();
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    // unlock方法没有while，因为while循环是为了自旋等待获得锁，已经有锁了就解锁
    public void myUnlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName()+"\t invoked myUnlock");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo(); // 还是线程操作资源类

        new Thread(() -> {
            spinLockDemo.myLock();
            // A线程获得锁之后，执行的过程中，B只能自旋等待A解锁才能获得锁
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
            spinLockDemo.myUnlock();
        }, "A").start();

        // main线程中这1s保证一定是A先获得锁，然后B获得锁
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            spinLockDemo.myLock();
            // B进入了lock方法，但是并不能获得锁，只能让A解锁之后B才能获得锁

            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
            spinLockDemo.myUnlock();
        }, "B").start();
    }
}
