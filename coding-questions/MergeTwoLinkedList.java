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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode helper = new ListNode(0);
        ListNode pre = helper;
        helper.next = l1;
        while(l1!=null && l2 != null)
        {
            if(l1.val>l2.val)
            {
                ListNode next = l2.next;
                l2.next = pre.next;
                pre.next = l2;
                l2 = next;
            }
            else
            {
                l1 = l1.next;
            }
            pre = pre.next;
     
        }
        if(l2!=null)
        {
            pre.next = l2;
        }
        return helper.next;
    }

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
                l2 = next;
            }
            else
            {
                next = l1.next;
                pre.next = l1;
                l1 = l1.next;
            }
            pre = pre.next;
     
        }
        if(l2!=null)
        {
            pre.next = l2;
        } else if (l1 != null) {
            pre.next = l1;
        }
        ListNode p = helper.next;
        while (p != null) {
            System.out.println(p);
            p = p.next;
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
	    
	    test.mergeTwoLists(l1, l2);
	}
}
