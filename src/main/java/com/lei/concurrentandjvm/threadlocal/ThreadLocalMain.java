package com.lei.concurrentandjvm.threadlocal;

public class ThreadLocalMain {
    private static void incrementSameThreadId() {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread()+"_"+i+",threadId:"+ThreadLocalId.get());
            }
        } finally {
                ThreadLocalId.remove();
        }
    }

    // 同一个变量i，每一个线程单独一个副本
    public static void main(String[] args) {
        incrementSameThreadId();
        new Thread(new Runnable() {
                @Override
                public void run() {
                    incrementSameThreadId();
                }
            }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                incrementSameThreadId();
            }
        }).start();
    }
}
