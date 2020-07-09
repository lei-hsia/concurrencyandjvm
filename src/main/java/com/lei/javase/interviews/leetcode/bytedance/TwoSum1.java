package com.lei.javase.interviews.leetcode.bytedance;

import java.util.HashMap;

public class TwoSum1 {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; ++i) {
            int num = target - nums[i];
            if (map.containsKey(num)) {
                res[0] = i;
                res[1] = map.get(num);
            }
            map.put(nums[i], i);
        }
        return res;
    }
}
