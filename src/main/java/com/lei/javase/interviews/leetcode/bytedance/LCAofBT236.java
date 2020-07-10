package com.lei.javase.interviews.leetcode.bytedance;

// 自我递归,不需要helper: 更明显: 递归函数看作黑匣子: 作用就是返回LCA
public class LCAofBT236 {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return left == null ? right : left;
    }
}
