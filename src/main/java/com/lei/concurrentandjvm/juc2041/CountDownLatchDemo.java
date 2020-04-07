package com.lei.concurrentandjvm.juc2041;

import java.util.concurrent.CountDownLatch;

/*
*   模拟离开教室，6个同学要先离开教室，班长最后离开教室锁门
*
*
*   CountDownLatch: 主要有两个方法，
*       1. 当一个或多个线程调用await: 这些线程会阻塞
*       2. 其他调用countDown的线程会使计数器减一(这个线程不会阻塞)
*       3. 计数器变为0，await方法阻塞的线程会被唤醒，继续执行
* */
public class CountDownLatchDemo {
}
