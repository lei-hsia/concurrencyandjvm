package com.lei.javase.interviews.leetcode.bytedance;

/* Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
    Output: Reference of the node with value = 8

    精髓: 长的变为短的那么长，两个同时遍历，因为长度相同且后面相同部分全部相同，所以碰到相同的了就是intersection
* */
public class IntersectionofTwoLinkedlists160 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        int lenA = len(headA), lenB = len(headB);
        if (lenA > lenB) {
            for (int i = 0; i < lenA - lenB; ++i) headA = headA.next;
        } else for (int i = 0; i < lenB - lenA; ++i) headB = headB.next;
        while (headA != null && headB != null && headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return headA;
    }

    public int len(ListNode head) {
        if (head == null) return 0;
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }
}
