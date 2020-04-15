package com.lei.concurrentandjvm.oom;


import java.nio.ByteBuffer;

/*   配置参数
     -Xmx5m -Xms5m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m

     故障现象

     原因:
     写NIO的程序经常用ByteBuffer读取／写入数据，这是一种基于通道(Channel)和缓冲区(buffer)的IO方式；
     ByteBuffer可以直接分配堆外内存，然后通过JVM堆内DirectByteBuffer对象作为这个对象的引用
     因为NIO是要从物理内存出去，直接分配在堆外:避免了Java堆和Native堆中来回分配数据，显著提高性能。

     ByteBuffer.allocate(capacity): 1. 分配JVM堆内存，属于GC，因为需要拷贝所以慢
     ByteBuffer.allocateDirect(capa): 2. 分配OS本地内存，不属于GC，不需要内存拷贝，所以快

     但是如果不断分配本地内存，堆内存很少使用，那么JVM就不GC，DirectByteBuffer 对象们就不会被回收；
     这个时候堆内存充足，但是本地内存可能用光了，地基不稳，再次尝试分配本地内存就OOM了
*
* */
public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        // NIO要ByteBuffer.allocateDirect最好先看看本地堆外内存最大是多少
        //System.out.println("配置的maxDirectMemory:"+(sun.misc.VM.maxDirectMemory() / (double)1024 / 1024) + "MB");
        // -XX:MaxDirectMemorySize=5m 分配5m，使用6m
        ByteBuffer bb = ByteBuffer.allocateDirect(6*1024*1024);
    }
}
