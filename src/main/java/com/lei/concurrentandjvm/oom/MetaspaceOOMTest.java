package com.lei.concurrentandjvm.oom;

import java.util.concurrent.TimeUnit;

/*  Metaspace是方法区在Hotspot中的实现，Metaspace并不在虚拟机内存中，而是使用本地内存;
    i.e. Java8中, class metadata (virtual machine's internal representation of Java class),
         被存储在叫做 metaspace 的 native memory

    Metaspace 存储下面这些信息:
        VM加载的类信息(类模版)
        常量池
        静态变量
        即时编译后的代码

     >>> 模拟metaspace溢出: 不断生成类加入metaspace中，类占据的空间总会超过metaspace的大小的
* */
public class MetaspaceOOMTest {
    static class OOMTest {  }

    public static void main(String[] args) {
        /*int i = 0;
        try {
            while (true) {
                i++;
                *//*Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor) {
                    ...
                }*//*
            }
        } catch (Throwable e) {
            System.out.println("********** 多少次后发生了异常"+i);
            e.printStackTrace();
        }*/
    }
}
