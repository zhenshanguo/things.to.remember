/*
Reverse a singly linked list.

click to show more hints.

Hint:

A linked list can be reversed either iteratively or recursively. Could you implement both?
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
    
    /* this is the reverse way */
    public ListNode reverseList(ListNode head) {

        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return head;
        }

		/* tail is important to remember the head's next, so that head can be attached to it after the reversion */
        ListNode tail = head.next;
        ListNode reversed = reverseList(head.next);

        // 前后翻转tail和head
        tail.next = head;
        head.next = null;

        return reversed;
    }
    
    /* this is the non recursive way */
    public ListNode reverseListNonRecursive(ListNode head) {

        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return head;
        }
        
        ListNode helper = new ListNode(0);
        helper.next = head;
        ListNode cur = head.next;
        
        //it's important to set head.next to null, otherwise, there will be endless loop or unexpected result
        head.next = null;
        
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = helper.next;
            helper.next = cur;
            cur = next;
        }

        return helper.next;
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
	    ListNode l21 = new ListNode(2);
	    ListNode l22 = new ListNode(4);
	    ListNode l23 = new ListNode(6);
	    l11.next = l12;
	    l12.next = l13;
	    l13.next = l14;
	    l14.next = l15;
	    l21.next = l22;
	    l22.next = l23;
	    ListNode rt = test.reverseListNonRecursive(l11);
	    System.out.println(test.printList(rt));
	}
}