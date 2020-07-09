package com.lei.javase.interviews.leetcode.bytedance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*  很好的题: 排序,比较,类型转化;  注: 因为modify许多小数组:其实是改变了res的值,而不改变intervals,所以创建新的list加入值
* */
public class MergeIntervals56 {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;

        Arrays.sort(intervals, new Comparator<int[]>(){ // 创建Comparator对象, 定义泛型　
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);

        for (int i = 1; i < intervals.length; ++i) {
            if (res.get(res.size() - 1)[1] < intervals[i][0]) res.add(intervals[i]);
            else {
                res.get(res.size() - 1)[1]= Math.max(res.get(res.size() - 1)[1], intervals[i][1]);
            }
        }

        // ArrayList --> Array: xxx.toArray(new xxxArray)
        // array -> arrayList:  Arrays.asList(someArray)
        return res.toArray(new int[res.size()][2]);
    }
}
