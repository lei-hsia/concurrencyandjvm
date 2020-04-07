package com.lei.concurrentandjvm.juc2041;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/*
        CountDownLatch: 减法得到0开始；
        CyclicBarrier: 反过来，加法加到barrier触发
            > 简而言之: 集齐七颗龙珠，就能召唤神龙
* */
public class CyclicBarrierDemo {
    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, ()->{ System.out.println("****收集到七颗龙珠，召唤神龙"); });

        for (int i = 0; i < 7; i++) {
            final int finalI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"\t 收集到第"+ finalI +"颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
