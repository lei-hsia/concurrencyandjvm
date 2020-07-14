package com.lei.javase.interviews.leetcode.bytedance;

/*  Given 1->2->3->4, you should return the list as 2->1->4->3.
*/
public class SwapNodesinPairs24 {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = new ListNode(0), prehead = cur; // cur: moving
        cur.next = head;
        while (cur.next != null && cur.next.next != null) {
            ListNode p1 = cur.next;
            ListNode p2 = cur.next.next;
            cur.next = p2;
            p1.next = p2.next;
            p2.next = p1;
            cur = p1;
        }
        return prehead.next;
    }
}
