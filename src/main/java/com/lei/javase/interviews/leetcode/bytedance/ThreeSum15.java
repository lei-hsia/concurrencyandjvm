package com.lei.javase.interviews.leetcode.bytedance;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
Find all unique triplets in the array which gives the sum of zero.

Note:
The solution set must not contain duplicate triplets.

Example:
Given array nums = [-1, 0, 1, 2, -1, -4],
A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
* */
public class ThreeSum15 {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList();
        int len = nums.length;
        if (len==0||nums[0]>0||nums[len-1]<0) return res;

        for (int k = 0; k < len-2; ++k) {
            if (nums[k] > 0) break;
            if (k>0 && nums[k] == nums[k-1]) continue;
            int target = -nums[k];
            int i = k+1, j = len-1;
            while (i < j) {
                if (nums[i] + nums[j] == target) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k])); // 如何自动装箱把一堆nums组成的数组作为list加进来
                    while (i < j && nums[i] == nums[i+1]) ++i;
                    while (i < j && nums[j] == nums[j-1]) --j;
                    // 需要进一步 ++i --j,因为while只是为了去重, 本来就需要 ++i; --j
                    ++i; --j;
                } else if (nums[i] + nums[j] < target) ++i;
                else --j;
            }
        }

        return res;
    }
}
