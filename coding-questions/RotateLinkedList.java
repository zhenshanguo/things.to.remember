/*
Given a list, rotate the list to the right by k places, where k is non-negative.

For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.
*/

/* the tricky part is to form the cycle */

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
    
    public ListNode rotateRight(ListNode head, int k) {

        if (head == null)
            return null;

        int len = 1;

        ListNode tail = head;

        while (tail.next != null) {
            len++;
            tail = tail.next;
        }
        
        // form a cycle 
        tail.next = head; 

        k %= len;

        /* from head, move towards tail for len-k-1 steps, so head will be the 
           previous node of the target node. the reason we use the previous node
           is that we need it to cut the cycle */
        for (int i = 1; i < len - k; i++) {
            head = head.next;
        }

        try {
            return head.next; //head.next is the head of the result list
        } finally {
            head.next = null; // break the cycle between head and the 'new' head
        }
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
	    ListNode l21 = new ListNode(7);
	    ListNode l22 = new ListNode(8);
	    ListNode l23 = new ListNode(9);
	    l11.next = l12;
	    l12.next = l13;
	    l13.next = l14;
	    l14.next = l15;
	    l15.next = l21;
	    l21.next = l22;
	    l22.next = l23;
	    ListNode rt = test.rotateRight(l11, 3);
	    System.out.println(test.printList(rt));
	}
}