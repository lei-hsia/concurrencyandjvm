package com.lei.concurrentandjvm.jvmgcjmm2044;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Print {
    // 模块／函数之间的精准调用，都需要flag, 通知的时候更改flag，conditionX表示对应线程X
    private int flag = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void printA() throws InterruptedException {
        lock.lock();
        try {
            // 判断
            while (flag != 1) {
                condition1.await(); // 线程1等待
            }
            // 干活
            for (int i = 0; i < 2; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            // 通知
            flag = 2;
            condition2.signal(); // 精确唤醒线程2
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() throws InterruptedException {
        lock.lock();
        try {
            // 判断
            while (flag != 2) {
                condition2.await(); // 线程1等待
            }
            // 干活
            for (int i = 0; i < 3; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            // 通知
            flag = 3;
            condition3.signal(); // 精确唤醒线程2
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() throws InterruptedException {
        lock.lock();
        try {
            // 判断
            while (flag != 3) {
                condition3.await(); // 线程1等待
            }
            // 干活
            for (int i = 0; i < 4; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            // 通知
            flag = 1;
            condition1.signal(); // 精确唤醒线程2
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/*
    要求: 多线程，调用顺序: A-B-C, A打印2次，B3次，C四次，这样来10轮
*/
public class ProdConsumerThreadOrder {
    public static void main(String[] args) {
        Print print = new Print();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    print.printA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "A").start();

            new Thread(() -> {
                try {
                    print.printB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "B").start();

            new Thread(() -> {
                try {
                    print.printC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "C").start();
        }
    }
}
