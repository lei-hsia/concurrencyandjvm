package com.lei.javase.interviews.leetcode.bytedance;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*  Input: [1,2,3,null,5,null,4]
    Output: [1, 3, 4]
    Explanation:

       1            <---
     /   \
    2     3         <---
     \     \
      5     4       <---
* */

// 右视图: 本质上level order traversal; tmp数组每次取出最后面的数，放到res即可

public class BTRightSideView199 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList();
        List<Integer> tmp = new ArrayList();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList();
        q.offer(root);
        while (!q.isEmpty()) {
            int count = q.size();
            for (int i = 0; i < count; ++i) {
                if (q.peek().left != null) q.offer(q.peek().left);
                if (q.peek().right != null) q.offer(q.peek().right);
                tmp.add(q.poll().val);
            }
            res.add(tmp.get(tmp.size() - 1));
            tmp.clear();
        }
        return res;
    }
}
