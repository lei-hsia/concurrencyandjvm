package com.lei.javase.interviews.leetcode.bytedance;

public class ConvertSortedArrayintoBST108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        return dfs(nums, 0, nums.length - 1);
    }
    public TreeNode dfs(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = (left + right) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = dfs(nums, left, mid - 1); // 子递归边界还是left/right,并不是0/len-1
        node.right = dfs(nums, mid+1,right);
        return node;
    }
}
