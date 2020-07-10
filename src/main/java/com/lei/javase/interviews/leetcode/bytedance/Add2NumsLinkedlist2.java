package com.lei.javase.interviews.leetcode.bytedance;

/*  Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
    Output: 7 -> 0 -> 8
    Explanation: 342 + 465 = 807.

    创建三个节点: head不动，每次在循环中创建cur节点，prev根据cur后移，创建出完整linkedlist
    while循环: 记住: 有carry的时候把carry也放在while中：用 || 作为条件: 统一处理，只要有一个不为空就继续循环,不过在循环体中每次都要判断
* */
public class Add2NumsLinkedlist2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode prev = new ListNode(0); // prev: 循环中创建cur每次向前移动，head不动
        ListNode head = prev;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) { // 统一: 全部都在循环中
            ListNode cur = new ListNode(0); // 创建新节点: move forward
            int sum = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
            cur.val = sum % 10;
            carry = sum / 10;
            prev.next = cur;
            prev = cur;

            l1 = (l1 == null) ? null : l1.next;
            l2 = (l2 == null) ? null : l2.next;
        }
        return head.next;
    }
}
