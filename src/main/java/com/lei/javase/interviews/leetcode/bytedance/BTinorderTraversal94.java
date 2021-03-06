package com.lei.javase.interviews.leetcode.bytedance;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class BTinorderTraversal94 {
    public List<Integer> recursiveInorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        dfs(res, root);
        return res;
    }
    public void dfs(List<Integer> res, TreeNode node){
        if (node == null) return;
        if (node.left != null) dfs(res, node.left);
        res.add(node.val);
        if (node.right != null) dfs(res, node.right);
    }

    public List<Integer> iterativeInorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        if (root == null) return res;
        Stack<TreeNode> st = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !st.empty()) {
            while (cur != null) {
                st.push(cur);
                cur = cur.left;
            }
            cur = st.pop();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }
}
