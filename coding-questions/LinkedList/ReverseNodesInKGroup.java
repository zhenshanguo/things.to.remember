/*
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

You may not alter the values in the nodes, only nodes itself may be changed.

Only constant memory is allowed.

For example, Given this linked list: 1->2->3->4->5

For k = 2, you should return: 2->1->4->3->5

For k = 3, you should return: 3->2->1->4->5
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
    
    /* recursive way */
    public ListNode reverseKGroup2(ListNode head, int k) {
        if (head == null || head.next == null || k < 2)
            return head;

        ListNode next_group = head;
        for (int i = 0; i < k; ++i) {
            if (next_group != null)
                next_group = next_group.next;
            else
                return head;
        }
        // next_group is the head of next group
        // new_next_group is the new head of next group after reversion
        ListNode new_next_group = reverseKGroup(next_group, k);
        ListNode prev = null, cur = head;
        while (cur != next_group) {
            ListNode next = cur.next;
            // this is actually reverse the pointer direction
            cur.next = prev != null ? prev : new_next_group;
            // move both prev and cur to their next
            prev = cur;
            cur = next;
        }
        return prev; // prev will be the new head of this group
    }
    
    /* iterative way */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k < 2) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // end pointer is the last node in the K-group. 
        for(ListNode prev = dummy, end = head; end != null; end = prev.next) {
            for (int i = 1; i < k && end != null; i++)
                end = end.next;
            if (end == null) break;  // 不足 k 个

            prev = reverse2(prev, prev.next, end);
        }

        return dummy.next;
    }

    // prev 是 first 前一个元素, [begin, end] 闭区间，保证三者都不为 null
    // 返回反转后的倒数第1个元素
    ListNode reverse(ListNode prev, ListNode begin, ListNode end) {
        ListNode end_next = end.next, p = begin.next; 
        begin.next = end.next;
        while (p != end_next) {
            ListNode pNext = p.next;
            p.next = prev.next;
            prev.next = p;
            p = pNext;
        }
        return begin;
    }
    
    // this is another way to do the reverse in the K-group, we basically change the 
    // direction of pointers between neighbor nodes
    ListNode reverse2(ListNode prev, ListNode begin, ListNode end) {
        ListNode end_next = end.next, q = begin, p = begin.next; 
        begin.next = end.next;
        while (p != end_next) {
            ListNode pNext = p.next;
            p.next = q;
            q = p;
            p = pNext;
        }
        prev.next = q;
        return begin;
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
	    ListNode l11 = new ListNode(1);
	    ListNode l12 = new ListNode(2);
	    ListNode l13 = new ListNode(3);
	    ListNode l14 = new ListNode(4);
	    ListNode l15 = new ListNode(5);
	    ListNode l21 = new ListNode(6);
	    ListNode l22 = new ListNode(7);
	    ListNode l23 = new ListNode(8);
	    l11.next = l12;
	    l12.next = l13;
	    l13.next = l14;
	    l14.next = l15;
	    l15.next = l21;
	    l21.next = l22;
	    l22.next = l23;
	    ListNode rt = test.reverseKGroup2(l11, 3);
	    System.out.println(test.printList(rt));
	}
}