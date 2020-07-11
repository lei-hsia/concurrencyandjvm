package com.lei.javase.interviews.leetcode.bytedance;

/*  Input:
        nums1 = [1,2,3,0,0,0], m = 3
        nums2 = [2,5,6],       n = 3

        Output: [1,2,2,3,5,6]

        因为如果从前往后会改变nums1，所以从后往前
* */
public class MergeSortedArray88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1, j = n-1, k = m+n-1;
        while (i >= 0 && j>=0) {
            if (nums1[i] > nums2[j]) nums1[k--] = nums1[i--];
            else nums1[k--] = nums2[j--];
        }
        while (j >= 0) nums1[k--] = nums2[j--]; // 可能nums1的大小都是nums2撑出来的但是nums1本身没有
    }
}
