package com.lei.concurrentandjvm.jmmjvmjucatomiclock20410;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try {
            // 判断
            while (number != 0) {
                condition.await();
            }
            // 干活
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            // 唤醒所有线程， 因为如果只唤醒一个，可能那个也是同向操作，就不行
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            // 判断
            while (number != 1) {
                condition.await();
            }
            // 干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            // 唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

/*
*   初始值为0，两个线程操作，一个加1一个减1，来5轮
* */
public class ProdConsumer_TraditionalDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.increment();
            }
        }, "AAA").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.decrement();
            }
        }, "BBB").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.increment();
            }
        }, "CCC").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                shareData.decrement();
            }
        }, "DDD").start();
    }
}
