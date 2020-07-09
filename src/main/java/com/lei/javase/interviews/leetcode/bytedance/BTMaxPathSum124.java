package com.lei.javase.interviews.leetcode.bytedance;

public class BTMaxPathSum124 {
    int maxValue;

    public int maxPathSum(TreeNode root) {
        // 如果创建一个新maxValue, maxPath()辅助函数用的是全局的maxValue更新之后并没有同步到maxValue, 所以会是 -2147483648
        //int maxValue = Integer.MIN_VALUE;
        maxValue = Integer.MIN_VALUE;
        maxPath(root);
        return maxValue;
    }

    public int maxPath(TreeNode node){
        if (node == null) return 0;
        int leftVal = Math.max(maxPath(node.left), 0);
        int rightVal = Math.max(maxPath(node.right), 0);
        maxValue = Math.max(maxValue, leftVal + rightVal + node.val);
        return Math.max(leftVal, rightVal) + node.val;
    }
}
