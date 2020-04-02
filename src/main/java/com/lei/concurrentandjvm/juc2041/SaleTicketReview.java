package com.lei.concurrentandjvm.juc2041;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket01 {
    // 高内聚: 要操作的对象的所有属性和方法都封装在类中 variables and methods
    private int number = 30;

    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            // for (int i = 0; i < 40; i++) { 条件判断错误
            if (number > 0) {
                System.out.println(Thread.currentThread().getName()+"卖掉第"+(number--)+" 还剩"+number);
            }
        } finally {
            lock.unlock();
        }
    }
}

public class SaleTicketReview {
    // 低耦合: 直接调用; 企业级代码: 线程操作资源类
    public static void main(String[] args) {

        Ticket01 ticket = new Ticket01();

        new Thread(() -> { for (int i = 0; i < 40; i++) { ticket.sale(); } },"A").start();
        new Thread(() -> { for (int i = 0; i < 40; i++) { ticket.sale(); } },"B").start();
        new Thread(() -> { for (int i = 0; i < 40; i++) { ticket.sale(); } },"C").start();
    }
}
