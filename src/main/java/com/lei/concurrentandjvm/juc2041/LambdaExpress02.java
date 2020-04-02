package com.lei.concurrentandjvm.juc2041;

// Java: OOP, 另一关键:  面向接口编程

import java.util.*;

@FunctionalInterface
interface Foo {
    // lambda表达式对应的参数就是正常的方法(非default,非static), 落地的方法也就是这个
    public int add(int x, int y);

//    public default int mul() {
//        return x * y;
//    };

    default int mul2(int x, int y) {
        return x * y;
    };

    static int div(int x, int y) {
        return x / y;
    }

    static int div2(int x, int y) {
        return x / y;
    }
}

/* 1 拷贝小括号，写死➡, 落地大括号
   2 @FunctionalInterface: 函数式编程接口: 接口中声明的方法只能1个: 这是显式声明，隐式声明: 不写，但是默认有
   3 default 可以有多个
*  4 static 可以有多个
* */
public class LambdaExpress02 {
    public static void main(String[] args) {
//        Foo foo = new Foo() { // 匿名内部类
//            @Override
//            public void sayHello() {
//                System.out.println("say hello");
//            }
//        };
//        foo.sayHello();
        Foo foo = (int x,int y) -> {
            System.out.println("come in add method");
            return x + y;
        };
        System.out.println(foo.add(3, 5));
        // System.out.println(foo.mul(3, 5));
        System.out.println(Foo.div(15, 3));

        // UUID.randomUUID()
    }
}
