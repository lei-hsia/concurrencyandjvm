package com.lei.concurrentandjvm.juc2041;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// synchronized体系: wait,notify:但是notify无法保证notify到哪一个线程
// lock体系: condition: await, signal; 因为condition是从lock实例衍生出来的并且可以定义多个，所以可以实现精准notify: signal

class ShareData {
    private int flag = 1; // A:1  B:2  C:3
    private Lock lock  = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5() throws InterruptedException {
        lock.lock();
        try {
            // 1  判断
            while (flag != 1) {
                c1.await();
            }
            // 2  干活
            for (int i = 0; i < 1; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            // 3  通知
            flag = 2; // 首先: 修改flag对应到下一个线程, 然后再通知那个线程
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() throws InterruptedException {
        lock.lock();
        try {
            // 1  判断
            while (flag != 2) {
                c2.await();
            }
            // 2  干活
            for (int i = 0; i < 2; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            // 3  通知
            flag = 3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() throws InterruptedException {
        lock.lock();
        try {
            // 1  判断
            while (flag != 3) {
                c3.await();
            }
            // 2  干活
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            // 3  通知
            flag = 1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/*
    题目: 三个线程ABC，需求: A线程打印1次，然后B打印2次，然后C打印3次，这样重复10轮
*/
public class ConditionDemo04 {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    shareData.print5();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    shareData.print10();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 15; i++) {
                    shareData.print15();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();
    }
}
