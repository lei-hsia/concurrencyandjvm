package com.lei.concurrentandjvm.oom;

public class StackOverFlowDemo {
    public static void main(String[] args) {
        stackOverflowError();
    }

    private static void stackOverflowError() {
        stackOverflowError();
    }
}
