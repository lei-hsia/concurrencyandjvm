package com.lei.javase.interviews.leetcode.bytedance;

/*Design an algorithm to find the maximum profit. You may complete as many transactions as you like
(i.e., buy one and sell one share of the stock multiple times).

Note: You may not engage in multiple transactions at the same time
(i.e., you must sell the stock before you buy again).*/

// 本质: 找到波谷到波峰的段，累加每段和
public class BestTimeBuySellStock122 {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int i = 0, buy, sell, profit = 0, n = prices.length-1;
        while (i < n) {
            while (i < n && prices[i+1] <= prices[i]) ++i;
            buy = prices[i];

            while (i < n && prices[i+1] > prices[i]) ++i;
            sell = prices[i];

            profit += (sell - buy);
        }
        return profit;
    }
}
