package com.lei.javase.interviews.leetcode.bytedance;

public class RemoveDupsfromLinkedlist83 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next; // 新的三数:不用后移cur
            } else cur = cur.next;
        }

        return head;
    }
}
