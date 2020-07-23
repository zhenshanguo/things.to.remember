/*
Merge Sort - Ideal for Linked List Sorting

Time Complexity: O(n log n), getMid is O(logn), and merging is O(n)
Space Complexity: O(log n) - Maximum depth of recursive function call stack. Merge sort implementation 
on Linked List avoids the O(n) auxiliary storage cost.
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
    
    // find the mid node and split the list into 2 list, merge sort each of them and then merge them
    public ListNode mergeSortList(ListNode head) 
    {
        if(head == null || head.next == null)
            return head;

        ListNode mid = getMid(head), second_head = mid.next; mid.next = null;

        ListNode L1 = mergeSortList(head);
		ListNode L2 = mergeSortList(second_head);

		return merge(L1, L2);
    }
    
    // merging 2 lists
    private ListNode merge(ListNode L1, ListNode L2)
	{
		ListNode dummy = new ListNode(0), current = dummy;
		
		while(L1 != null && L2 != null)
		{
			if(L1.val < L2.val)
			{
				current.next = L1;
				L1 = L1.next;
			}
			else
			{
				current.next = L2;
				L2 = L2.next;
			}
			current = current.next;
		}
		
		if(L1 != null) current.next = L1;
		if(L2 != null) current.next = L2;
		
		return dummy.next;
	}

    //using fast and slow pointer to find the mid node in the list
    private ListNode getMid(ListNode head)
    {
        ListNode slow = head, fast = head.next;
        
        while(fast != null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
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
	    ListNode l14 = new ListNode(8);
	    ListNode l15 = new ListNode(9);
	    ListNode l16 = new ListNode(5);
	    ListNode l17 = new ListNode(6);
	    ListNode l18 = new ListNode(4);
	    l11.next = l12;
	    l12.next = l13;
	    l13.next = l14;
	    l14.next = l15;
	    l15.next = l16;
	    l16.next = l17;
	    l17.next = l18;
	    ListNode rt = test.mergeSortList(l11);
	    System.out.println(test.printList(rt));
	}
	
}