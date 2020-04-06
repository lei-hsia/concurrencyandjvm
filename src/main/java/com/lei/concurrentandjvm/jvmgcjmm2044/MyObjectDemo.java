package com.lei.concurrentandjvm.jvmgcjmm2044;

public class MyObjectDemo {
    public static void main(String[] args) {

        MyObjectDemo objectDemo = new MyObjectDemo();
        System.out.println(objectDemo.getClass().getClassLoader().getParent().getParent());
        System.out.println(objectDemo.getClass().getClassLoader().getParent());
        System.out.println(objectDemo.getClass().getClassLoader());

        System.out.println();
        System.out.println();
        System.out.println();

        Object object = new Object();
        //System.out.println(object.getClass().getClassLoader().getParent().getParent()); //NullPointerException
        //System.out.println(object.getClass().getClassLoader().getParent());
        System.out.println(object.getClass().getClassLoader());
    }
}
