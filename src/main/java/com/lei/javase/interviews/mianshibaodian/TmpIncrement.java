package com.lei.javase.interviews.mianshibaodian;

public class TmpIncrement {
    public static void main(String[] args) {
        int j = 0;
        for (int i = 0; i < 100; i++) {
            j = j++;  /* <=> tmp = j; j = j + 1; j = tmp; */
        }
        System.out.println(j);
    }
}
