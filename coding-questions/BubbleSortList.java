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
    
    public ListNode bubbleSort(ListNode head) {
        if (head != null && head.next !=null) {
            boolean wasChanged;

            do {
                ListNode current = head;
                ListNode previous = null;
                ListNode next = head.next;
                wasChanged = false; //check if there is any node swap in the pass, if not, we can stop

                while ( next != null ) {
                    if (current.val > next.val) {
    
                        wasChanged = true;

                        if ( previous != null ) {
                            ListNode sig = next.next;

                            previous.next = next;
                            next.next = current;
                            current.next = sig;
                        } else {
                            ListNode sig = next.next;
                            /* // quite import as this is the swap between the first 
                            and second node in the original list, we need to update 
                            the head to point to the previously second node */
                            head = next; 
                            next.next = current;
                            current.next = sig;
                        }
                        // after swapping, previous should point to next, current is not changed, and the 
                        // new next should be the current's next
                        previous = next; 
                        next = current.next;
                    } else { 
                        // if no swapping, just keeping moving to the end
                        previous = current;
                        current = next;
                        next = next.next;
                    }
                } 
            } while( wasChanged ); // as long as there is one swap, we need to looping one more time
        }
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
	    ListNode rt = test.bubbleSort(l11);
	    System.out.println(test.printList(rt));
	}
	
}