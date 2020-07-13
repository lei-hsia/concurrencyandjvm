package com.lei.javase.interviews.leetcode.bytedance;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/* Given a non-empty array of integers, return the k most frequent elements.

*   Input: nums = [1,1,1,2,2,3], k = 2
    Output: [1,2]
* */
public class TopKFrequentElements347 {

    /* Use a priority queue. O(N log N)     */
    public int[] topKFrequent(int[] nums, int k) {
        if (nums.length == k) return nums;

        Map<Integer, Integer> count = new HashMap();
        for (int n : nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }

        // 频率从小到大: 保证heap大小为k, 每次poll都是poll频率小的，剩下k个就是top k
        Queue<Integer> heap = new PriorityQueue<>((n1, n2) -> count.get(n1) - count.get(n2));

        for (int n : count.keySet()) {
            heap.add(n);
            if (heap.size() > k) heap.poll();
        }

        int[] res = new int[k];
        for (int i = k-1; i>=0; --i) {
            res[i] = heap.poll();
        }
        return res;
    }


}
