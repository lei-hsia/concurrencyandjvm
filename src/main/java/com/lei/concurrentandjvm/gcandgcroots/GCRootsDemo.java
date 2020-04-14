package com.lei.concurrentandjvm.gcandgcroots;

/*  在Java中, 可以作为 GCRoots的有:
        1. VM 栈（栈中的本地变量表) 引用的对象
        2. 方法区中类静态变量引用的对象（static）
        3. 方法区中常量引用的对象(final)
        4. 本地方法栈JNI（native方法）引用的对象
* */
public class GCRootsDemo {

    private byte[] bytes = new byte[10 * 1024 * 1024]; // 1

    private static GCRootsDemo gcRootsDemo2; // 2
    private static final GCRootsDemo gcRootsDemo3 = new GCRootsDemo(); // 3

    private static void m1() {
        GCRootsDemo g1 = new GCRootsDemo();
        System.gc();
        System.out.println("第一次GC完成");
    }

    public static void main(String[] args) {
        m1();
    }
}
