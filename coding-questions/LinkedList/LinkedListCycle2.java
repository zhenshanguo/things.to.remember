/*
Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

Note: Do not modify the linked list.

Follow up:
Can you solve it without using extra space?
*/

/*
slow指针每次走1步，fast指针每次走2步。如果链表有环，那么两个指针一定会相遇。

设链表头到环入口结点的结点数目是a，环内的结点数目r。假设相遇时，fast指针已经绕环转了n圈，比slow多走了n*r步。假设环的入口结点到相遇结点的结点数目为x。

那么在相遇时，slow走了a+x步，fast走了a+x+n*r步。actually, slow could walk a+mr+x steps, and fast walks a+x+nr steps, and n>m

由于fast的步调是slow的两倍，所以有a+x = (n-m)*r。因而，a = (n-m)*r - x

显然，从相遇位置开始，走(n-m)*r - x步，一定可以到达环的入口结点；从链表头开始，走a步，也会到达环的入口。并且我们得到了a = (n-m)*r - x。所以我们让两个指针，
一个从相遇位置出发一个从链表头出发，让他们都单步前进。因为a = (n-m)*r - x，所以他们一定会在环的入口相遇。
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
    
    public ListNode detectCycle(ListNode head) {

        //check edge cases
        if (head == null) {
            return null;
        }

        ListNode slow = head;
        
        /* THE IMPLEMENTATION IS A BIT DIFFERENT, FAST AND SLOW DIDN'T START FROM HEAD TOGETHER, 
           FAST TAKES ONE STEP EARLIER, SO THE FORMULA WILL BE a+x+mr = a+x-1+nr, AND 
           a = (n-m)r-x-1 = (n-m)r-(x+1), SO THE FAST SHOULD START FROM THE NEXT NODE OF THE NODE
           THEY MEET THE FRIST TIME */ 
           
        ListNode fast = head.next;
        ListNode meetNode = null;

        boolean meet = false;

        // 判断是否有环
        while (fast != null) {

            if (fast.next == null || fast.next.next == null) {
                return null;
            }

            if (slow == fast) {
                meet = true;
                
                // HERE THE FAST NODE SHOULD START FROM THE NEXT NODE THE 'MEET' NODE BECAUSE THE DISCUSSION ABOVE
                meetNode = slow.next;
                System.out.println(meetNode.val);
                break;
            }

            fast = fast.next.next;
            slow = slow.next;
        }

        if (meet) {
            slow = head;
            fast = meetNode;

            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }

            return slow;
        }

        return null;
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
	    ListNode l21 = new ListNode(6);
	    ListNode l22 = new ListNode(7);
	    ListNode l23 = new ListNode(8);
	    l11.next = l12;
	    l12.next = l13;
	    l13.next = l14;
	    l14.next = l15;
	    l15.next = l21;
	    l21.next = l22;
	    l22.next = l23;
	    l23.next = l21;
	    ListNode rt = test.detectCycle(l11);
	    System.out.println(rt.val);
	}
}