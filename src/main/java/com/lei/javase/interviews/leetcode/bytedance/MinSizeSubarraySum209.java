package com.lei.javase.interviews.leetcode.bytedance;

/* Given an array of n positive integers and a positive integer s,
   find the minimal length of a contiguous subarray of which the sum ≥ s. If there isn't one, return 0 instead.
    e.g.
    Input: s = 7, nums = [2,3,1,2,4,3]
    Output: 2
    Explanation: the subarray [4,3] has the minimal length under the problem constraint.

    完整遍历一遍数组，并且双指针left指针尽量向右压缩，得到 min length
* */
public class MinSizeSubarraySum209 {

    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0) return 0;
        int n = nums.length, res = n + 1, left = 0, sum = 0;
        for (int i = 0; i < n; ++i) {
            sum += nums[i];
            while (sum >= s) {
                res = Math.min(res, i - left + 1); // 长度
                sum -= nums[left++];
            }
        }
        return res == n+1 ? 0 : res;
    }
}
