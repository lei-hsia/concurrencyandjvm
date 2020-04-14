package com.lei.concurrentandjvm.ref;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/*  对于 >>只有<< 弱引用的对象来说，只要GC一运行，不管JVM是否足够，都会回收该对象占用的内存
* */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        Object obj1 = new Object();
        //SoftReference<Object> softReference = new SoftReference<>(obj1);
        WeakReference<Object> weakReference = new WeakReference<>(obj1);
        System.out.println(obj1);
        //System.out.println(softReference);
        System.out.println(weakReference);

        obj1 = null;
        System.gc(); // obj置为null: 其实也只是强引用赋值为null，但是那个对象还是在; 所以要手动GC才会让对象失去强引用的引用
        System.out.println("==============");

        System.out.println(obj1);
        //System.out.println(softReference.get());
        System.out.println(weakReference);
        System.out.println(weakReference.get());
    }
}
