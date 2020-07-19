/*
Given a sorted linked list, delete all duplicates such that each element appear only once.

For example,
Given 1->1->2, return 1->2.
Given 1->1->2->3->3, return 1->2->3.
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

        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return head;
        }

        ListNode node = head;

        while (node.next != null) {

            // 如果元素不重复，跳过
            if (node.val != node.next.val) {
                node = node.next;
            } else {
                // 重复，则跳过下一个
                while (node.next != null && node.val == node.next.val) {
                    node.next = node.next.next;
                }
            }
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
	    ListNode l12 = new ListNode(3);
	    ListNode l13 = new ListNode(3);
	    ListNode l14 = new ListNode(3);
	    ListNode l15 = new ListNode(1);
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