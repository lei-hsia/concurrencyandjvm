package com.lei.concurrentandjvm.ref;

import java.lang.ref.SoftReference;


/*  软引用: 可以让对象豁免一些垃圾回收；对于只有软引用的对象来说：
        1. 系统内存充足时，不会被回收
        2. ／／／／不足时，会被回收
    软引用通常用在对内存敏感的程序, e.g. 高速缓存，内存够用就缓存，不够就回收; mybatis源码中

    reference.get()获得这个引用指向的对象
    总结: 内存够，不收；不够，就收
* */
public class SoftReferenceDemo {

    public static void softRef_Memory_Enough() {
        Object obj1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(obj1);

        obj1 = null;
        System.gc();

        System.out.println(obj1);
        System.out.println(softReference);
    }
    /*
    *   JVM配置, 故意产生大对象并配置小内存，内存不够导致OOM，看 软引用 回收情况
    *   -Xms5m -Xmx5m -XX:+PrintGCDetails
    * */
    public static void softRef_Memory_NotEnough() {
        Object obj1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(obj1);
        System.out.println(obj1);
        System.out.println(softReference);

        obj1 = null;

        try {
            byte[] bytes = new byte[30 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(obj1);
            System.out.println(softReference.get()); //get:Returns this reference object's referent; 引用本身还是在的，但是引用引用的对象被回收了
        }
    }

    public static void main(String[] args) {
        //softRef_Memory_Enough();
        softRef_Memory_NotEnough();
    }
}
