package com.lei.concurrentandjvm.jvmgcjmm2044;

/*
    JMM本身是没有自带同步各线程工作内存中的共享变量到主内存中的: 要用锁，或者轻量级的volatile;
    JMM是什么?
        : 线程的变量操作(读写)，不能直接操作主内存变量, 而是要在自己的工作内存中完成:
             共享变量: 主内存复制到自己的工作内存，操作，然后变量写回主内存; 各线程工作内存中储存的主内存中的变量拷贝副本 (注意必须是共享变量才是这样)

    JMM关于同步的规定:
        1. 线程解锁前，必须把共享变量的值刷新回主内存
        2. ...加锁前，必须读取主内存最新的值到自己的工作内存
        3. 加锁解锁，是同一把锁;

    JMM = 可见性(通知机制)
* */

class MyNumber {
    volatile int number = 10;

    public void changeTo120() {
        number = 120;
    }
}

public class JMMDemo {
    public static void main(String[] args) {
        // main as B thread

        MyNumber myNumber = new MyNumber(); // 线程操作资源类

        new Thread(() -> {
            System.out.println("*************** come in thread A");
            try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }
            myNumber.changeTo120();
            System.out.println(myNumber.number);
        }, "A").start();

        while (myNumber.number == 10) {
            // 没有volatile, 这里print 120 之后主线程因为一直在while里面不出去，因为没有通知让主线程知道改了，要用volatile使得主内存中的值改变变得可见
        }

        System.out.println("main mission is over");
    }
}
