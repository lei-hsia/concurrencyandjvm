package com.lei.concurrentandjvm.juc2041;

class Phone {
    public synchronized void sendEmail() throws Exception {
        System.out.println("**** send email");
    }

    public synchronized void sendSMS() throws Exception {
        System.out.println(">>>> send SMS");
    }
}

public class ProdConsumerDemo04 {
    public static void main(String[] args) {

    }
}
