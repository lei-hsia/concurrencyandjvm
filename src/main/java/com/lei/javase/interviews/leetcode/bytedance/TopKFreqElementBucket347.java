package com.lei.javase.interviews.leetcode.bytedance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFreqElementBucket347 {

    public List<Integer> topKFrequent(int[] nums, int k) {
        // bucket并没有实例化，先声明一个List数组，每个bucket是一个list，后面对每个freq声明初始化一个ArrayList实例
        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> map = new HashMap();

        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        for (int key : map.keySet()) {
            int freq = map.get(key);
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList();
            }
            bucket[freq].add(key);
        }

        List<Integer> res = new ArrayList();

        for (int pos = bucket.length-1; pos>=0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }

        return res;
    }
}
