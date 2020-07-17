/*
Time Complexity: O(n^2)
Space Complexity: O(1) - Insertion sort is In-Place sorting algorithm
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
    
    /* this method is doing in-place sorting, insertion sort, and the complexity is O(n^2), and space is O(1) */
    public ListNode insertionSortList(ListNode head)
    {
        // Initialize partially sorted list
		ListNode dummy = new ListNode(0), prev = dummy, current = head;

        while(current != null)
        {
            /* when new current node is smaller than previous pre node, we need to reset pre
               node to the dummy node, meaning, we will do the check from the head again because
               we can't traverse the list backward */
            if(prev.val > current.val)
                prev = dummy;
            
            // Find the right place to insert current node, where prev.next is equal or bigger than current
            // then current should be between prev and prev.next
            while(prev.next != null && prev.next.val < current.val) {
                prev = prev.next;
            }

            // Insert current between prev and prev.next
            ListNode nextNode = current.next;
			current.next = prev.next;
            prev.next = current;
            current = nextNode;
            System.out.println("dummy: " + printList(dummy));
            System.out.println("prev: " + printList(prev));
            System.out.println("current: " + printList(current));
            System.out.println("\n");
        }
        return dummy.next;
    }
    
    /* this one is easier to understand, but not as efficient as the above one */
    public ListNode insertionSortList2(ListNode head)
    {
        // Initialize partially sorted list
		ListNode dummy = new ListNode(0); dummy.next = head;
        ListNode current = head;

        while(current != null && current.next != null)
        {
			if(current.val > current.next.val)
			{
				ListNode prev = dummy;
                ListNode target = current.next;
                
                // Find the right place to insert current node
                while(prev.next != null && prev.next.val < target.val)
					prev = prev.next;
			
	            // Insert target between prev and prev.next
                current.next = target.next;
				target.next = prev.next;
                prev.next = target;
			}
			else
			{
				current = current.next;
			}
        }
        return dummy.next;
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
	    ListNode rt = test.insertionSortList(l11);
	    System.out.println(test.printList(rt));
	}
	
}