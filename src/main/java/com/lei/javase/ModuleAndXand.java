package com.lei.javase;

public class ModuleAndXand {
    public static void main(String[] args) {
        long timeMillis = System.currentTimeMillis();
        int a=0;
        int times = 100000 * 10000;
        for (long i = 0; i < times; i++) {
            a = 9999 % 1024;
        }
        long timeMillis2 = System.currentTimeMillis();

        int b=0;
        for (long i = 0; i < times; i++) {
            b = 9999 & (1024-1);
        }
        long timeMillis3 = System.currentTimeMillis();

        System.out.println(a + ", " + b);
        System.out.println("%: " + (timeMillis2 - timeMillis));
        System.out.println("&: "  + (timeMillis3 - timeMillis2));
    }
}
