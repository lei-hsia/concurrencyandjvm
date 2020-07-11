package com.lei.javase.interviews.leetcode.bytedance;

import java.util.LinkedList;
import java.util.List;

public class BTzigzagLevelOrderTraversal103 {
    // recursive
    public List<List<Integer>> recursiveZigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList();
        dfs(res, root, 0);
        return res;
    }
    public void dfs(List<List<Integer>> res, TreeNode root, int level) {
        if (root == null) return;
        if (level >= res.size()) res.add(new LinkedList());
        if (level % 2 == 0) {
            res.get(level).add(root.val);
        } else res.get(level).add(0 ,root.val);
        if (root.left != null) dfs(res, root.left, level+1);
        if (root.right != null) dfs(res, root.right, level+1);
    }
}
