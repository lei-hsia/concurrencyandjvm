package com.lei.javase.interviews.leetcode.bytedance;

/*Find the kth largest element in an unsorted array.
Note that it is the kth largest element in the sorted order, not the kth distinct element.

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
*/

// 精髓: QuickSort找pivot, sort之后 如果左边有k-1个大于pivot的数，就OK
public class KthLargestNumArray215 {
    public int findKthLargest(int[] nums, int k) {
        int left = 0, right = nums.length -1;
        while (true) {
            int pos = partition(nums, left, right);
            if (pos == k-1) return nums[pos];
            if (pos > k-1) right = pos-1;
            else left = pos + 1;
        }
    }
    public int partition(int[] nums, int left, int right) {
        int pivot = nums[left], l = left + 1, r = right;
        while (l <= r) {
            if (nums[l] < pivot && nums[r] > pivot) {
                int tmp = nums[l];
                nums[l] = nums[r];
                nums[r] = tmp;
                l++; r--;
            }
            if (nums[l] >= pivot) ++l;
            if (nums[r] <= pivot) --r;
        }
        int tmp = nums[left];
        nums[left] = nums[r];
        nums[r] = tmp;
        return r;
    }
}
