/* 
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

For example,
Given 1->2->3->3->4->4->5, return 1->2->5.
Given 1->1->1->2->3, return 2->3.
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
    
    public ListNode deleteDuplicates(ListNode head) {

        /* handling edge cases */
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return head;
        }
        
        int val = head.val;

        ListNode node = head;

        boolean killme = false; // whether to delete the head node

        while (node.next != null && node.next.val == val) {
            /* keep searching till the next node is different or next node is null */
            node = node.next;
            killme = true;
        }

        /* if current node is duplicate, the new head will be the recursice result of node.next 
           otherwise, head.next will be the recursive result of node.next */
        if (killme) {
            head = deleteDuplicates(node.next);
        } else {
            head.next = deleteDuplicates(node.next);
        }

        return head;
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
	    ListNode l12 = new ListNode(1);
	    ListNode l13 = new ListNode(2);
	    ListNode l14 = new ListNode(3);
	    ListNode l15 = new ListNode(3);
	    ListNode l21 = new ListNode(2);
	    ListNode l22 = new ListNode(4);
	    ListNode l23 = new ListNode(6);
	    l11.next = l12;
	    l12.next = l13;
	    l13.next = l14;
	    l14.next = l15;
	    l21.next = l22;
	    l22.next = l23;
	    ListNode rt = test.deleteDuplicates(l11);
	    System.out.println(test.printList(rt));
	}
}