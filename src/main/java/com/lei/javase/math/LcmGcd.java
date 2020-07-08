package com.lei.javase.math;

public class LcmGcd {
    public static int gcd(int p, int q) {
        if (q == 0) return p;
        return gcd(q, p%q);
    }

    public static int lcm(int p, int q) {
        return p*q / gcd(p, q);
    }

    public static void main(String[] args) {
        int a=12, b = 42;
        System.out.println(gcd(a, b));
        System.out.println(lcm(a, b));
    }
}
