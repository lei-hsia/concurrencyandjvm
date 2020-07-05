package com.lei.javase.interviews.nowcoderexe;

import java.util.Scanner;

/*  Z国的货币系统包含面值1元、4元、16元、64元共计4种硬币，以及面值1024元的纸币。
    现在小Y使用1024元的纸币购买了一件价值为N (0 < N \le 1024)N(0<N≤1024)的商品，请问最少他会收到多少硬币？
* */
public class MinCoinNumber {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
        num = 1024 - num;
        int count = 0;
        int[] coins = {64, 16, 4, 1};
        for(int i = 0; i < 4; ++i) {
            count += num/ coins[i];
            num = num % coins[i];
        }
        System.out.println(count);
        String a;

    }
}
