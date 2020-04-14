package com.lei.concurrentandjvm.jmmjvmjucatomiclock20410;

class HoldLockThread implements Runnable{
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    // 类中有资源: 变量和操作: synchronized代码块也需要在方法中
    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName()+"\t 持有"+lockA+"尝试获得lockB");
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName()+"\t 持有"+lockB+"尝试获得lockA");
            }
        }
    }

}

/*  死锁是指两个或两个以上的线程在执行过程中，由于竞争资源或者由于
    彼此通信而造成的一种阻塞的现象，若无外力作用，它们都将无法推进下去
* */
public class DeadLockDemo {
    public static void main(String[] args) {
        new Thread(new HoldLockThread("lockA", "lockB"),"AAA").start();
        new Thread(new HoldLockThread("lockB", "lockA"),"BBB").start();
    }
}
