/*
 Given a linked list, swap every two adjacent nodes and return its head.

For example,
Given 1->2->3->4, you should return the list as 2->1->4->3.

Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
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
    
    public ListNode swapPairs(ListNode head) {

        // handling edge cases
        if (head == null || head.next == null) {
            return head;
        }
        // define a helper node
        ListNode fakeHead = new ListNode(0);
        fakeHead.next = head;

        // p1 will be the previous node of the pair
        ListNode p1 = fakeHead;
        // p2 is the first node of the pair
        ListNode p2 = head;

		// we actually swap p1.next with p2.next
        while (p2 != null && p2.next != null) {
            ListNode nextStart = p2.next.next; // the first node of the next pair
            p2.next.next = p2; //point the second node's next to the first node of current pair
            p1.next = p2.next; //point the previous node's next to the second node of currnet pair
            p2.next = nextStart; //point 'new' second node's next to the first node of the nex pair
            p1 = p2; //point p1, pervious node, to the new second node of current pair
            p2 = p2.next; //point p2 to the first node of the next pair
        }

        return fakeHead.next;
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
	    ListNode rt = test.swapPairs(l11);
	    System.out.println(test.printList(rt));
	}
}