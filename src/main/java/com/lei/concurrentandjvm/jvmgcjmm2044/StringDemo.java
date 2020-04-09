package com.lei.concurrentandjvm.jvmgcjmm2044;

public class StringDemo {
    public static void main(String[] args) {
        /* Error: Main method not found in class java.lang.String, please define the main method as:
           public static void main(String[] args)
        or a JavaFX application class must extend javafx.application.Application*/
        // 找不到main方法，所以是从bootstrap classloader；看出来双亲委派机制
        System.out.println(">>>>> error: rt.jar: bootstrap classloader: no main method in Java original");


    }
}
