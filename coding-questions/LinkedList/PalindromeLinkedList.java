/*
Given a singly linked list, determine if it is a palindrome.

Follow up:
Could you do it in O(n) time and O(1) space?
*/

/*
O(n) time and O(1) space的解法：

找到链表中点
将后半段链表翻转
对比两段链表
说明：不用管链表是奇数还是偶数，如果链表长度是偶数，那么翻转后，前段和后段链表长度相等；如果是奇数，后段链表长1个结点，所以只需要判断前段结点是否遍历结束。
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
    
    public boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null) {
            return true;
        }

        ListNode slow = head;
        ListNode fast = head;

        // 找到链表中点
        /* when there are odd number of nodes in the list, slow is the mid node, otherwise, 
           slow points to the (n/2 + 1)th node */
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        System.out.println(printList(slow));
        System.out.println(printList(head));
        
        // 翻转后段链表
        /* here, the list was not terminated at the mid node, so the head is still the original 
           whole list, but it will be terminated at the mid point during the reversion */
           
        ListNode tail = reverseList(slow);
        
        System.out.println(printList(tail));
        
		/* when head == slow, it means they meet at the last node, this happens when there are odd
		   number of nodes in the list. when there are even number of nodes, both head and slow will 
		   reach to null */
		   
        while (head != slow) {
            if (head.val != tail.val) {
                return false;
            }
            head = head.next;
            tail = tail.next;
        }

        return true;
    }

    /* QUITE IMPORTANT TO REMEMBER HOW TO REVERSE A LIST */
    public ListNode reverseList(ListNode head) {

        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return head;
        }

		/* need to remember that head.next which after reverse will become the last node, and 
		   we need to attach head node to it */
        ListNode tail = head.next;
        ListNode reversed = reverseList(head.next);

        // 前后翻转tail和head
        tail.next = head;
        head.next = null; //set head.next to null, this is why we don't need to terminate 'head' node in the middle above
        
        return reversed;
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
	    ListNode l13 = new ListNode(5);
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
	    boolean rt = test.isPalindrome(l11);
	    System.out.println(rt);
	}
}