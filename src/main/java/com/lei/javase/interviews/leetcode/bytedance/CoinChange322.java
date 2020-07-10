package com.lei.javase.interviews.leetcode.bytedance;

public class CoinChange322 {
    // a: amount; f数组: 对应每个amount
    public int coinChange(int[] coins, int a) {
        // dp 数组 : 对应每个a
        int n = coins.length;
        if (n == 0) return -1;
        int[] f = new int[a+1];
        f[0] = 0; // f[a]
        for(int i = 1; i <= a; ++i) {
            f[i] = a+1;
            for (int j = 0; j < n; ++j) {
                if (i >= coins[j]) {
                    f[i] = Math.min(f[i], f[i - coins[j]] + 1);
                }
            }
        }

        return f[a] == a+1 ? -1 : f[a];
    }
}
