package com.lei.concurrentandjvm.jmmjvmjucatomiclock20410;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone {
    public synchronized void sendSMS() throws Exception {
        System.out.println(">>>>>>> send SMS");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println("******* send email");
    }

    // ********************* lock *********************
    Lock lock = new ReentrantLock();


    public void run() {
        get();
    }

    private void get() {
        lock.lock();
        try {
            System.out.println(">>>>>>> invoke get");
            set();
        } finally {
            lock.unlock();
        }
    }

    private void set() {
        lock.lock();
        try {
            System.out.println(">>>>>>>> invoke set");
        } finally {
            lock.unlock();
        }
    }
}

public class ReentrantLockDemo {
    public static void main(String[] args) {

        Phone phone = new Phone();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A").start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        // 资源类中用synchronized方法，或者lock加锁递归进入所有加相同锁的方法，线程操作资源类
        new Thread(() -> {
            phone.run();
        }, "B").start();
    }
}
