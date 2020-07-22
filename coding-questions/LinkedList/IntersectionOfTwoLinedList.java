/*
Write a program to find the node at which the intersection of two singly linked lists begins.

For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗            
B:     b1 → b2 → b3
begin to intersect at node c1.

Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.
*/

/*
记链表A的长度是lenA，最后一个结点为p；链表B的长度是lenB，最后一个结点为q。

如果p≠q，则链表A、B不相交，直接返回null。因为如果链表A、B在某处相交，那么后面的结点完全相同（如题目中所示，是Y型的）。

如果p=q，则链表A、B在某处相交。让长的链表走|lenA−lenB|步（按照末端对齐），然后两个链表同时向前走，相等结点即为相交公共结点。
*/

import java.util.*;
import java.lang.*;

public class Main
{
    private static class ListNode {
        public int val;
        public ListNode next;
        
        public ListNode(int val) {
            this.val = val;
        }
        
        public String toString() {
            return String.valueOf(this.val);
        }
    }
    
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        if (headA == null || headB == null) {
            return null;
        }
    
        // 计算链表A的长度
        int lenA = 1;
        ListNode p = headA;
        while (p.next != null) {
            lenA++;
            p = p.next;
        }
    
        // 计算链表B的长度
        int lenB = 1;
        ListNode q = headB;
        while (q.next != null) {
            lenB++;
            q = q.next;
        }
    
        // 若A和B的最后一个结点不等，则不相交，返回null
        if (p != q) {
            return null;
        }
    
        // 链表按照尾部对齐
        if (lenA > lenB) {
            int t = lenA - lenB;
            while (t-- != 0) {
                headA = headA.next;
            }
        } else {
            int t = lenB - lenA;
            while (t-- != 0) {
                headB = headB.next;
            }
        }
    
        // 同时向前走
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
    
        return headA;
    }
    
    public String printList(ListNode rt) {
        StringBuffer sb = new StringBuffer();
        while (rt!=null) {
    	    sb.append(rt + "->");
    	    rt = rt.next;
	    }
	    return sb.toString();
    }
    
	public static void main(String[] args) {
	    Main test = new Main();
	    ListNode l11 = new ListNode(-1);
	    ListNode l12 = new ListNode(4);
	    ListNode l13 = new ListNode(3);
	    ListNode l14 = new ListNode(6);
	    ListNode l15 = new ListNode(4);
	    ListNode l21 = new ListNode(5);
	    ListNode l22 = new ListNode(6);
	    ListNode l23 = new ListNode(4);
	    l11.next = l12;
	    l12.next = l13;
	    l13.next = l14;
	    l14.next = l15;
	    l21.next = l22;
	    l22.next = l23;
	    ListNode rt = test.getIntersectionNode(l11, l21);
	    System.out.println(rt.val);
	}
}