package com.lei.concurrentandjvm.jvmgcjmm2044;

public class InterviewInheritance02 {
    public static char text = 'a';

    public InterviewInheritance02(){
        System.out.println('c');
    }

    {
        System.out.println('b');
    }

    static{
        System.out.println(text);
    }
}
