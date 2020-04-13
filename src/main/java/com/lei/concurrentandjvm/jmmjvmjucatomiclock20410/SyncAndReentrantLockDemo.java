package com.lei.concurrentandjvm.jmmjvmjucatomiclock20410;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResources {
    private int flag = 1;
    private Lock lock = new ReentrantLock();

    public void print2() {
        lock.lock();
        try {
            //
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

    }
}
