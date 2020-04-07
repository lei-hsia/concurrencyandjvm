package com.lei.concurrentandjvm.jvmgcjmm2044;

public class InterviewInheritanceSon extends InterviewInheritance02 {
    public static String text2 = "a1";

    public InterviewInheritanceSon() {
        System.out.println("c2");
    }

    {
        System.out.println("b2");
    }

    static {
        System.out.println(text2);
    }

    public static void main(String[] args) {
        InterviewInheritanceSon son = new InterviewInheritanceSon();
    }

}
