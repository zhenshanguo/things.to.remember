/* 
Given a linked list, determine if it has a cycle in it.

Follow up:
Can you solve it without using extra space?
*/

/* 快慢指针，定义两个指针，一个每次走一步，另一个每次走两步。如果快指针“遇到”了慢指针，说明有环。 */

/* when a linked list has cycle, there must be a node in it, that has 2 other nodes linked 
   to it (2 nodes' next nodes), we can define a map with each node as the key and number of 
   'inbound' links as the value, when we see an entry with value =2 in map, there is a cycle */
   
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
    
    public boolean hasCycle(ListNode head) {

        if (head == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null) {

            if (fast.next == null || fast.next.next == null) {
                return false;
            }

            if (slow == fast) {
                return true;
            }

            fast = fast.next.next;
            slow = slow.next;
        }

        return false;
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
	    ListNode l14 = new ListNode(6);
	    ListNode l15 = new ListNode(4);
	    ListNode l21 = new ListNode(5);
	    ListNode l22 = new ListNode(6);
	    ListNode l23 = new ListNode(4);
	    l11.next = l12;
	    l12.next = l13;
	    l13.next = l14;
	    l14.next = l15;
	    l15.next = l21;
	    l21.next = l22;
	    l22.next = l23;
	    l23.next = l14;
	    boolean rt = test.hasCycle(l11);
	    System.out.println(rt);
	}
}