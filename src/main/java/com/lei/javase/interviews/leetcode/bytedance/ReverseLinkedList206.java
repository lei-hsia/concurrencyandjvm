package com.lei.javase.interviews.leetcode.bytedance;

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class ReverseLinkedList206 {
    public ListNode reverseList(ListNode head) {
        // head只能next,所以prev创建应该在while循环外面,在前, 后面都放在while循环中
        ListNode prev =  null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
    }
}
