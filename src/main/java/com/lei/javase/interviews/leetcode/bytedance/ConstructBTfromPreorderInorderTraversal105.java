package com.lei.javase.interviews.leetcode.bytedance;


// pre+1: 左子树根节点; 左子树所有节点数 = index - inStart

public class ConstructBTfromPreorderInorderTraversal105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(0, 0, preorder.length -1, preorder, inorder);
    }
    public TreeNode build(int pre, int in1, int in2, int[] preorder, int[] inorder) {
        if (pre > preorder.length - 1 || in1 > in2) return null;
        int index = 0;
        for (int i = in1; i <= in2; ++i) {
            if (inorder[i] == preorder[pre]) {
                index = i; break;
            }
        }
        TreeNode root = new TreeNode(inorder[index]);
        root.left = build(pre+1, in1, index-1, preorder, inorder);
        root.right = build(pre+1+index-in1, index+1, in2, preorder, inorder);
        return root;
    }
}
