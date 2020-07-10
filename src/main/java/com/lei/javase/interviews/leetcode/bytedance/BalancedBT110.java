package com.lei.javase.interviews.leetcode.bytedance;

/*Given a binary tree, determine if it is height-balanced
        a binary tree in which the left and right subtrees of every node differ in height by no more than 1.*/

// 看深度: 还是以当前node为核心, 往下递归; 当前node为root是balanced <=> 左右高度差<=1并且左右子树都是balanced的

// 出发点还是节点!!! 递归函数看作黑匣子: 作用就是你想他有的作用

public class BalancedBT110 {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int left = depth(root.left);
        int right = depth(root.right);
        return (Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right));

    }

    public int depth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }
}
