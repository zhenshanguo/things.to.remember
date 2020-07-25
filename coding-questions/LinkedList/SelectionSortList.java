/*
Selection Sort

Time Complexity: O(n^2)
Space Complexity: O(1) - Selection sort is In-Place sorting algorithm
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
    
    //looping each node in the list, and swap current node value with the smallest from the rest of list
    public ListNode selectionSortList(ListNode head)
    {
		for(ListNode current = head; current != null; current = current.next)
		{
			swap(current, findMinimumNode(current));
		}
        return head;
	}

    //swap the values of 2 nodes
	private void swap(ListNode x, ListNode y)
    {
        if(x != y)
        {
            int temp = x.val;
            x.val = y.val;
            y.val = temp;    
        }
    }

    //find the node with the smallest value
	private ListNode findMinimumNode(ListNode head)
	{
		if(head.next == null)
			return head;
		
        ListNode minimumNode = head;
        
		for(ListNode current = head.next; current != null; current = current.next)
		{
			if(minimumNode.val > current.val)
				minimumNode = current;
		}
		return minimumNode;
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
	    ListNode rt = test.selectionSortList(l11);
	    System.out.println(test.printList(rt));
	}
	
}