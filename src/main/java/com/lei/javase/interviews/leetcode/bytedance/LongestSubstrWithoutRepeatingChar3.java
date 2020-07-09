package com.lei.javase.interviews.leetcode.bytedance;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstrWithoutRepeatingChar3 {

    public int lengthOfLongestSubstring(String s) {
        int i = 0, j = 0, max = 0;
        Set<Character> set = new HashSet<>();
        while (j < s.length()) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                max = Math.max(max, set.size()); // 包含的时候会remove，每次set size会减小;
                System.out.println(max);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return max;
    }
}
