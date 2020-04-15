package com.lei.concurrentandjvm.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceDemo {
    public static void main(String[] args) {
        Object object = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(object, referenceQueue);

        System.out.println(object);
        System.out.println(phantomReference.get()); // always null
        System.out.println(referenceQueue.poll());

        System.out.println("=====================");

        object = null;
        System.gc();

        System.out.println(object);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
    }
}
