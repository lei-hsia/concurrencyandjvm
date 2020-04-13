package com.lei.concurrentandjvm.jmmjvmjucatomiclock20410;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResources {
    private int flag = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print2() {
        lock.lock();
        try {
            // 判断
            while (flag != 1) {
                condition1.await();
            }
            // 干活
            for (int i = 0; i < 2; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+flag);
            }
            // 唤醒
            flag++; // 改变状态
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print3() {
        lock.lock();
        try {
            // 判断
            while (flag != 2) {
                condition2.await();
            }
            // 干活
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+flag);
            }
            // 唤醒
            flag++; // 改变状态
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print4() {
        lock.lock();
        try {
            // 判断
            while (flag != 3) {
                condition3.await();
            }
            // 干活
            for (int i = 0; i < 4; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+flag);
            }
            // 唤醒
            flag = 1; // 改变状态
            condition1.signal();
            System.out.println();
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/*
*               synchronized  & ReentrantLock 区别:
*       1.   JVM层面, monitorenter/monitorexit; API层面
*       2. 使用方法: 不需要手动释放锁  需要
*       3.    不可中断         可以中断: lockInterruptibly, trylock(timeout)
*       4.     非公平锁         非公平锁／公平锁 都可以
*       5.  精准唤醒:  不能       可以
* */
// 要求: 多线程按顺序调用: A2次->B3次->C4次:
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResources shareResources = new ShareResources();

        for (int i = 0; i < 5; i++) {
            // 这一句没有flag标识，所以不能确定会在ABC顺序中的哪里打出来
            // System.out.println(">>>>>>>> the"+i+"th round <<<<<<<<<<");
            new Thread(() -> {
                shareResources.print2();
            }, "AA").start();

            new Thread(() -> {
                shareResources.print3();
            }, "BB").start();

            new Thread(() -> {
                shareResources.print4();
            }, "CC").start();


        }
    }
}
