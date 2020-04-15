package com.lei.concurrentandjvm.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class ReferenceQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        Object obj1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(obj1, referenceQueue);

        System.out.println(obj1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("=================");

        obj1 = null;
        System.gc(); // soft, weak, phantom: GC，自己就没有了，但是回收之前播种到queue中 (如果不GC那么强引用在, soft/weak都在)
        Thread.sleep(500);

        System.out.println(obj1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());


        /*String[] a = new String[2];
        Object[] b = a;
        a[0] = "h";
        // Storing element of type 'java.lang.Integer' to array of 'java.lang.String' elements may produce 'ArrayStoreException'
        b[1] = Integer.valueOf(42);
        System.out.println(b);
        System.out.println(a);*/
    }
}
