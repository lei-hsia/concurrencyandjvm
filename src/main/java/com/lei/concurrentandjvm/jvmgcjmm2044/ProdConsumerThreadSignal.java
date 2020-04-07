package com.lei.concurrentandjvm.jvmgcjmm2044;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Air {

    private int flag = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            // 判断
            while (flag != 0) {
                condition.await();
            }
            // 干活
            flag++;
            System.out.println(Thread.currentThread().getName()+"\t"+flag);
            // 通知
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
        lock.lock();
        try {
            // 判断
            while (flag == 0) {
                condition.await();
            }
            // 干活
            flag--;
            System.out.println(Thread.currentThread().getName()+"\t"+flag);
            // 通知
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

public class ProdConsumerThreadSignal {

    public static void main(String[] args) {
        Air air = new Air();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    air.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    air.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
    }
}
