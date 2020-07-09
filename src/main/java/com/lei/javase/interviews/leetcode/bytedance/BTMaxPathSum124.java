package com.lei.javase.interviews.leetcode.bytedance;

// 看出来: 不管是什么样奇怪的东西，都是dfs的思路，以当前node为输入变量
public class BTMaxPathSum124 {
    int maxValue;

    public int maxPathSum(TreeNode root) {
        // 如果创建一个新maxValue, maxPath()辅助函数用的是全局的maxValue更新之后并没有同步到maxValue, 所以会是 -2147483648
        //int maxValue = Integer.MIN_VALUE;
        maxValue = Integer.MIN_VALUE;
        dfs(root);
        return maxValue;
    }

    public int dfs(TreeNode root){
        if (root == null) return 0;
        int left = Math.max(0, dfs(root.left));
        int right = Math.max(0, dfs(root.right));
        // 是否以当前node为顶点的一条path整个sum最大, i.e.以cur为root的新路径
        maxValue = Math.max(maxValue, left + right + root.val);
        return Math.max(left, right) + root.val; //但是只能选一条
    }
}
