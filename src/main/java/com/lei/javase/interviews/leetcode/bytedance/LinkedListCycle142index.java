package com.lei.javase.interviews.leetcode.bytedance;

public class LinkedListCycle142index {
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                /* 三段: head到entrypoint; entry到meet; meet回到entry: x1=x3下面fast/slow同速相遇就是用的这个
                * 当然: 可以有倍数关系，不过不影响*/
                slow = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast; // fast == slow: meet
            }
        }
        return null;
    }
}
