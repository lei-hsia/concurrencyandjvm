package com.lei.concurrentandjvm.ref;

/*
*   内存不足时，JVM开始垃圾回收；
*   对于强引用的对象，就算是出现OOM也不会回收♻️，死都不收，只要被强引用可达；因此强引用是java内存不足的主要原因
* */
public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object ref1 = new Object(); // strong reference
        Object ref2 = ref1; // reference assignment
        ref1 = null;
        System.gc();
        System.out.println(ref1);
        System.out.println(ref2); // object
    }
}
