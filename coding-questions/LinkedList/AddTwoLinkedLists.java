/*
You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

		//check edge case first
        if (l1 == null && l2 == null) {
            return null;
        }

        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        ListNode p1 = l1;
        ListNode p2 = l2;

		// define the variable for carry over
        int carry = 0;

		// this head pointer is quite important to move along when iterating the lists
        ListNode head = new ListNode(0);
        //we also need one pointer to point to the head all the time, so we can return the head of the result list
        ListNode result = head;

        while (carry != 0 || p1 != null || p2 != null) {

            int v1 = 0;
            if (p1 != null) {
                v1 = p1.val;
                p1 = p1.next;
            }

            int v2 = 0;
            if (p2 != null) {
                v2 = p2.val;
                p2 = p2.next;
            }

            int tmp = v1 + v2 + carry;
            carry = tmp / 10;
            head.next = new ListNode(tmp % 10);
            head = head.next;
        }

        return result.next;
    }
    
	public static void main(String[] args) {
	    Main test = new Main();
	    ListNode l11 = new ListNode(2);
	    ListNode l12 = new ListNode(4);
	    ListNode l13 = new ListNode(3);
	    ListNode l21 = new ListNode(5);
	    ListNode l22 = new ListNode(6);
	    ListNode l23 = new ListNode(4);
	    l11.next = l12;
	    l12.next = l13;
	    l21.next = l22;
	    l22.next = l23;
	    ListNode rt = test.addTwoNumbers(l11, l21);
	    while (rt!=null) {
    	    System.out.println(rt);
    	    rt = rt.next;
	    }
	}
	
}