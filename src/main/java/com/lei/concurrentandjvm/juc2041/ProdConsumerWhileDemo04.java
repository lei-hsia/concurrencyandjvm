package com.lei.concurrentandjvm.juc2041;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AirCondition {
    private int number = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void increment() throws InterruptedException{
        lock.lock();
        try {
            // 1. 判断
            while (number != 0) {
                condition.await(); // this.wait();
            }
            // 2. 干活
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            // 3. 通知
            condition.signalAll(); // notifyAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException{
        lock.lock();
        try {
            // 1. 判断
            while (number == 0) {
                condition.await(); // this.wait();
            }
            // 2. 干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            // 3. 通知
            condition.signalAll(); // notifyAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    /*public synchronized void increment() throws InterruptedException {
        // 1. 判断: 多线程中的flag只能用while判断，并不能用if判断
        while (number != 0) {
            // 如果这里的判断只是一次,是if而不是while,那么可能AA CC两个+的操作都进来了，都在wait, decr之后两个都加了，就变成2了
            this.wait();
        }
        // 2. 干活
        number++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        // 3. 通知
        notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        // 1
        while (number == 0) {
            this.wait();
        }
        // 2.
        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        // 3.
        notifyAll();
    }*/
}

/*  题目要求: 两个线程, 一个对number进行加1操作，另一个减1，要求交替进行，最后出现010101效果


      多线程编程:
      1. 高内聚低耦合，线程操作资源类
      2. 判断 干活 通知: while 判断，每次判断完成之后拉出循环重新判断
      3. 防止虚假唤醒

两个线程只可能出现0101的交替，即使资源类中的判断用的是if判断: 因为不是＋1就是－1，所以其实并没有其他的选择
而如果是4个线程: e.g. 此时执行的是＋1，但是其他进来的线程可能还是＋1，虽然都堵在wait方法上面，但是一旦变回0之后，＋1都会
执行，所以就变成了2
*/
public class ProdConsumerWhileDemo04 {
    public static void main(String[] args) {
        AirCondition airCondition = new AirCondition();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airCondition.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airCondition.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airCondition.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    airCondition.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "D").start();
    }
}
