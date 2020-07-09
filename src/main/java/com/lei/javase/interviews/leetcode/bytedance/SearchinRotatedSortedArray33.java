package com.lei.javase.interviews.leetcode.bytedance;

/*  关键: nums[mid], target和nums[0]是否在"same side":
        如果在same side那么并不影响简单的二分查找，如果不在 那么调整使得中间的nums[mid]变成应该在的二分查找的那边

    4 5(tar) 6 7(mid) 0 1 2: nums[mid]>nums[0], target>nums[0], 要进行BS的一边是OK的

    5 6 7 0(mid) 1 2(tar) 4: 都小于nums[0], 要进行BS的一边(右边)也是正常的, 直接BS

    4 5 6 7(mid) 0 1(tar) 2: nums[mid]和target相对nums[0]在两边, 那么要进行BS的一边少，不能直接BS;
                             target < nums[0], 说明应该去右边进行BS,
                             而nums[mid]>nums[0], 那么把mid位置变为小于nums[0]的那边，而且应该在右边也是最小: 变为 -INF

    5 6(tar) 7 0(mid) 1 2 4: 在nums[0]两边, target>nums[0], BS应该往左, nums[mid]小了，变大，变最大: INF
* */

// 在以某个点进行pivot之后的有序数组中 二分查找
public class SearchinRotatedSortedArray33 {
    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;

            int num = (nums[mid] < nums[0]) == (target < nums[0]) ?
                    nums[mid] : target < nums[0] ? Integer.MIN_VALUE : Integer.MAX_VALUE;

            if (num < target) left = mid + 1;
            else if (num > target) right = mid;
            else return mid;
        }
        return -1;
    }
}
