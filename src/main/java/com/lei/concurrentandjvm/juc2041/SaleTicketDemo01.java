package com.lei.concurrentandjvm.juc2041;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket {
    private int number = 30;

    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName()+"\t卖出第"+(number--)+"还剩"+number);
            }
        } finally {
            lock.unlock();
        }
    }
}

/*
*   企业级代码: 高内聚低耦合, i.e. 线程操作资源类;
*
*   资源类: 自封装实例变量+实例方法，所以叫高内聚, 不依赖外部，所以叫低耦合
* */

public class SaleTicketDemo01 {
    public static void main(String[] args) { // main只是一个资源入口，并不操作什么东西
        // main中的第一行: 总是创建资源类
        final Ticket01 ticket = new Ticket01();

        // Thread(Runnable target, String name): Allocate a new thread object.

        /*@FunctionalInterface
        public interface Runnable {
            public abstract void run();
        */


        new Thread(() -> {for (int i = 0; i <40; i++) ticket.sale(); }, "A").start();

//        new Thread(new Runnable() {
//            public void run() {
//                for (int i = 0; i <40; i++) { // 大于上面的30就行了
//                    ticket.sale();
//                }
//            }
//        }, "A").start();  // thread调用start方法并不会直接就开始运行: 哪个线程运行取决于底层OS和CPU交互决定
//
//        new Thread(new Runnable() {
//            public void run() {
//                for (int i = 0; i <40; i++) {
//                    ticket.sale();
//                }
//            }
//        }, "B").start();
//
//        new Thread(new Runnable() {
//            public void run() {
//                for (int i = 0; i <40; i++) {
//                    ticket.sale();
//                }
//            }
//        }, "C").start();
    }
}
