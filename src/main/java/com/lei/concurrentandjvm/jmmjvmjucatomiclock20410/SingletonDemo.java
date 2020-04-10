package com.lei.concurrentandjvm.jmmjvmjucatomiclock20410;

/*
*   单例模式: 需要使用间接使用new，即getInstance方法。这是一个设计方式的代表，而不仅仅指代一个方法名
*
*   getInstance这个方法在单例模式用的甚多，为了避免对内存造成浪费，直到需要实例化该类的时候才将其实例化，所以用getInstance来获取该对象，
 *  至于其他时候，也就是为了简便而已，为了不让程序在实例化对象的时候，不用每次都用new关键字，索性提供一个instance方法，不必一执行这个类就
 *  初始化，这样做到不浪费系统资源
* */
public class SingletonDemo {
    private static SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName()+"\t inside constructor");
    }

    private static SingletonDemo getInstance() {
        if (instance == null) {
            instance = new SingletonDemo();
        }
        return instance;
    }

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("single thread");
        }, "AA").start();
    }
}
