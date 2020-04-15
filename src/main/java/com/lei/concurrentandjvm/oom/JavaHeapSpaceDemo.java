package com.lei.concurrentandjvm.oom;

import java.util.Random;

public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
        String string = "lei";

        while (true) {
            string += string + new Random().nextInt(11111111) + new Random().nextInt(23456789);
            string.intern();
        }

    }
}
