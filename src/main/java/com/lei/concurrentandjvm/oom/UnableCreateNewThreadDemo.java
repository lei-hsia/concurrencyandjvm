package com.lei.concurrentandjvm.oom;

import java.util.concurrent.TimeUnit;

/*  高并发经常: java.lang.OutOfMemoryError: unable to create new native thread
    native thread异常与对应的平台相关, Linux默认一个进程最多创建1024线程，但是因为file handler, etc.
    导致其实最多也就900出头个线程，安全起见，手动创建线程最多不要超过600个.

    复习: thread.start()底层调用start0方法，不能重复调用start方法, 因为: IllegalThreadStateException
* */
public class UnableCreateNewThreadDemo {
    public static void main(String[] args) {
        for (int i = 0; ; i++) {
            System.out.println("***********"+i);

            new Thread(() -> {
                try { TimeUnit.SECONDS.sleep(1000000); } catch (InterruptedException e) { e.printStackTrace(); }
            }, ""+i).start();
        }
    }
}
