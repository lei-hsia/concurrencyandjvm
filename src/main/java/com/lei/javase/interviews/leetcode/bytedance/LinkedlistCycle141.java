package com.lei.javase.interviews.leetcode.bytedance;

public class LinkedlistCycle141 {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) { // fast != null: e.g. [1,2]: fast本身不能为null,null没有next
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }
}
