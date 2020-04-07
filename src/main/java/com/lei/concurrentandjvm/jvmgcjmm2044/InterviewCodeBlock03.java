package com.lei.concurrentandjvm.jvmgcjmm2044;



class CodeZY {
    public CodeZY() {
        System.out.println("Code的构造方法 111");
    }
    {
        System.out.println("Code的构造块 222");
    }
    static {
        System.out.println("Code的静态代码块 333");
    }
}
public class InterviewCodeBlock03 {
    {
        System.out.println("InterviewCodeBlock03的构造块 444");
    }
    static {
        System.out.println("InterviewCodeBlock03的静态代码块 555");
    }
    public InterviewCodeBlock03() {
        System.out.println("InterviewCodeBlock03的构造方法 666");
    }

    public static void main(String[] args) {
        System.out.println("InterviewCodeBlock03的main方法777");
        new CodeZY();
        System.out.println("----------------");
        new CodeZY();
        System.out.println("----------------");
        new InterviewCodeBlock03();
    }
}
