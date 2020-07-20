/*
Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.
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
    
    public ListNode reverseBetween(ListNode head, int m, int n) {

        if (head == null || head.next == null || m == n) {
            return head;
        }

        ListNode fakeHead = new ListNode(-1);
        fakeHead.next = head;

        // 先向后移m-1步, after the loop, pre points to the node right before mth node
        ListNode pre = fakeHead;
        for (int i = 1; i < m; i++) {
            pre = pre.next;
        }

        // for the n-m nodes after mth node, move them one by one after pre node 
        ListNode mNode = pre.next; // mth node is after pre node
        for (int i = m; i < n; i++) {
        	// cur node is the next node of mNode, and is the one to be move to after pre node
            ListNode cur = mNode.next;
            //switch mNode and cur node
            mNode.next = cur.next; // after this, mNode 
            cur.next = pre.next;
            pre.next = cur;
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
	    ListNode rt = test.reverseBetween(l11, 3, 7);
	    System.out.println(test.printList(rt));
	}
}