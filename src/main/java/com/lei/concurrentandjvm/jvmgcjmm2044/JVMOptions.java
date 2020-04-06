package com.lei.concurrentandjvm.jvmgcjmm2044;

import java.util.Random;

public class JVMOptions {
    public static void main(String[] args) {

        // Runtime: Runtime Data Area: java抽象成了一个对象:就是这个类
        System.out.println(Runtime.getRuntime().availableProcessors());

        long maxMemory = Runtime.getRuntime().maxMemory(); // JVM试图使用的最大内存量
        long totalMemory = Runtime.getRuntime().totalMemory();// JVM 内存总量
        System.out.println("-Xmx:MAX_MEMORY = "+maxMemory+" (字节), "+ (maxMemory/(double) 1024 / 1024) + "MB");
        System.out.println("-Xms:TOTAL_MEMORY = "+totalMemory+" (字节), "+ (totalMemory/(double) 1024 / 1024) + "MB");

        String str = "www.atguigu.com/lei";
        while (true) {
            str += str + new Random().nextInt(88888888) + new Random().nextInt(9999999);
        }

        //byte[] bytes = new byte[40 * 1024 * 1024];
    }
}
