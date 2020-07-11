package com.lei.javase.interviews.leetcode.bytedance;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*  Input: [100, 4, 200, 1, 3, 2]
    Output: 4
    Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
*/

// HashSet初始化: HashSet(Collection<? extends E> c)
public class LongestConsecutiveSequence128 {

    // 用set更简单: 找到最小值, 逐渐往上加到最大值，得到长度
    public int longestConsecutiveSet(int[] nums) {
        Integer[] newNums = new Integer[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            newNums[i] = Integer.valueOf(nums[i]);
        }
        int res = 0;
        Set<Integer> s = new HashSet<>(Arrays.asList(newNums));  // set加入的时候需要装箱类型转换
        for (int i : s) {
            if (!s.contains(i-1)) {
                int j = i + 1;
                while (s.contains(j)) {
                    ++j;
                }
                res = Math.max(res, j - i);
            }
        }
        return res;
    }

    public int longestConsecutiveMap(int[] nums) {
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap();
        for (int n : nums) {
            if (!map.containsKey(n)) {
                int left = (map.containsKey(n - 1) ? map.get(n - 1) : 0);
                int right = (map.containsKey(n + 1) ? map.get(n + 1) : 0);
                int sum = left + right + 1;
                map.put(n, sum);
                res = Math.max(res, sum);

                map.put(n - left, sum); // 这两行和下面的continue: 表明只用更新边界; 因为新来的数如果是边界就更新，不是边界就continue
                map.put(n + right, sum);
            }
            else continue;
        }
        return res;
    }
}
