package com.lei.concurrentandjvm.jvmgcjmm2044;

class AirCondition {

    private int number = 0;

    // 高内聚低耦合，对资源类的操作方法都封装在资源类中
    public synchronized void increment() throws InterruptedException {
        // 1. 判断
        while (number != 0) {
            this.wait();
        }
        // 2. 干活
        number++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        // 3. 通知
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        // 1. 判断
        while (number == 0) {
            this.wait();
        }
        // 2. 干活
        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        // 3. 通知
        this.notifyAll();
    }

}

public class ProdConsumerThread {
    public static void main(String[] args) {
        AirCondition air = new AirCondition();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    air.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    air.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    air.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    air.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "D").start();
    }
}
