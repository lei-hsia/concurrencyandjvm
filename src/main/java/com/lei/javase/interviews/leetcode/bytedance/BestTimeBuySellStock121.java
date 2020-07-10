package com.lei.javase.interviews.leetcode.bytedance;

/*If you were only permitted to complete at most one transaction
(i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
Note that you cannot sell a stock before you buy one.*/

// i.e. 遍历一次数组， 纪录前面的最小值，每次更新res
public class BestTimeBuySellStock121 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;
        int res = 0, min = prices[0];
        for (int i = 1; i < n; ++i) {
            res = Math.max(res, prices[i] - min);
            min = Math.min(min, prices[i]);
        }
        return res;
    }
}
