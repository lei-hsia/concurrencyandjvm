package com.lei.javase.interviews.leetcode.bytedance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class BTlevelOrderTraversal {

    public List<List<Integer>> recursiveLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        dfs(res, root, 0);
        return res;
    }
    public void dfs(List<List<Integer>> res, TreeNode node, int level) {
        if (node == null) return;
        if (level >= res.size()) res.add(new ArrayList());
        res.get(level).add(node.val);
        if (node.left != null) dfs(res, node.left, level+1);
        if (node.right != null) dfs(res, node.right, level+1);
    }

    public List<List<Integer>> iterativeLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList();
        q.offer(root);
        while (!q.isEmpty()) {
            int count = q.size();
            List<Integer> arr = new ArrayList();
            for (int i = 0; i < count; ++i) {
                // 看队列首位的元素，然后把此元素的左右子节点都加入队列
                if (q.peek().left != null) q.offer(q.peek().left);
                if (q.peek().right != null) q.offer(q.peek().right);
                arr.add(q.poll().val);
            }
            res.add(arr);
        }
        return res;
    }
}
