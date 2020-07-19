/*
Given a linked list, remove the nth node from the end of list and return its head.

For example,

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.

Note:
Given n will always be valid.
Try to do this in one pass.
*/

/*
题目中说n是合法的，就不用对n进行检查了。用标尺的思想，两个指针相距为n，前一个到表尾 (first.next == null)，
则后一个到了要删除的前一个。(前一个为first，后一个为second，)

指针first, second指向链表头部； 
移动first n times before pointer second； 
同时移动first and second，使first到表尾 (first.next == null)； 
删除second.next(second.next = second.next.next)。
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
    
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        // handle edge cases
        if (head == null || head.next == null) {
            return null;
        }
        
        // when n==0, we return the list head
        if (n==0) {
            return head;
        }
    
        ListNode first = head;
        ListNode second = head;
    
        // the first pointer moves n times before the second pointer
        for (int i = 0; i < n; i++) {
            first = first.next;
            
            /* in case the first pointer is null, like n equals or bigger than the length 
            of the list, we delete the head of the list */
            if (first == null) {
                return head.next;
            }
        }
    
        // move both pointers till first.next is null (hit the end of the list)
        while (first.next != null) {
            first = first.next;
            second = second.next;
        }
        // now second is actually pointing to the previous node of the node to be deleted
        second.next = second.next.next;
    
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
	    ListNode rt = test.removeNthFromEnd(l11,5);
	    System.out.println(test.printList(rt));
	}
}
