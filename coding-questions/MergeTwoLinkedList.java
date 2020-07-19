/* 
Merge two sorted linked lists and return it as a new list. The new list should be made by 
splicing together the nodes of the first two lists.
*/

import java.util.Arrays;
import java.lang.Math;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;
import java.util.LinkedList;

public class Main
{
	/* this method takes one list a the main list, and the other as the branch and merge the branch
	   list to the main one */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode helper = new ListNode(0);
        ListNode pre = helper;
        helper.next = l1; // takes l1 as the main list
        while(l1!=null && l2 != null)
        {
            if(l1.val>l2.val) // if l2 is smaller, we add it behind of pre
            {
                ListNode next = l2.next; // remember l2.next
                l2.next = pre.next; // put l2 between pre and its next, should be same as l1
                pre.next = l2; // set l2 as pre's next
                l2 = next; // set l2 as its next
            }
            else
            {
                l1 = l1.next; // if l1 is smaller, move it to next
            }
            pre = pre.next; // no matter which one is smaller/bigger between l1 and l2, pre move to its next
     
        }
        if(l2!=null)
        {
            pre.next = l2;
        }
        return helper.next;
    }

	/* this method is even easier to understand, it's merging the 2 lists to a new list */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode helper = new ListNode(0);
        ListNode pre = helper;
        ListNode next = null;
        while(l1!=null && l2 != null)
        {
            if(l1.val>l2.val)
            {
                next = l2.next;
                pre.next = l2;
                l2.next = null; // this line can be commented out, but with it, it is cleaner
                l2 = next;
            }
            else
            {
                next = l1.next;
                pre.next = l1;
                l1.next = null; // this line can be commented out, but with it, it is cleaner
                l1 = next;
            }
            pre = pre.next;
     
        }
        if(l2!=null)
        {
            pre.next = l2;
        } else if (l1 != null) {
            pre.next = l1;
        }
        return helper.next;
    }
    
    private static class ListNode {
        public int val;
        public ListNode next;
        
        public String toString() {
            return String.valueOf(this.val);
        }
        public ListNode(int val) {
            this.val = val;
        }
    }

	public static void main(String[] args) {
	    Main test = new Main();
	    ListNode l1 = new ListNode(1);
	    l1.next = new ListNode(2);
	    l1.next.next = new ListNode(4);
	    ListNode l2 = new ListNode(1);
	    l2.next = new ListNode(3);
	    l2.next.next = new ListNode(4);
	    
	    test.mergeTwoLists2(l1, l2);
	}
}
