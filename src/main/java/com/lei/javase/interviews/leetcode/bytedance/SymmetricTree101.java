package com.lei.javase.interviews.leetcode.bytedance;

/*  e.g.    1
           / \
          2   2
         / \ / \
        3  4 4  3
* */

import java.util.Stack;

public class SymmetricTree101 {

    // recursive
    public boolean recursiveIsSymmetric(TreeNode root) {
        if (root == null) return true;
        return judge(root.left, root.right);
    }
    public boolean judge(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null || left.val != right.val) return false;
        return judge(left.left, right.right) && judge(left.right, right.left);
    }

    // 对称回文：一看就stack
    public boolean iterativeIsSymmetric(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> st = new Stack();
        st.push(root.left); st.push(root.right);
        while (!st.empty()) {
            TreeNode n1 = st.pop(); TreeNode n2 = st.pop();
            if (n1 == null && n2 == null) continue; // 这里并不能return，应该开始新的一轮循环
            if (n1 == null || n2 == null || n1.val != n2.val) return false; // 一边递归完另一边没有
            st.push(n1.right);
            st.push(n2.left);
            st.push(n1.left);
            st.push(n2.right);
        }
        return true; // 全加入并最后pop出去,stack为空: 说明上面全是对称的，叶子节点所有children都是null,整个树对称
    }
}
