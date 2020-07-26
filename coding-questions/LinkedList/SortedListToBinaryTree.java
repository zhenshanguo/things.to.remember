/* 这个题是二分查找树的题目，要把一个有序链表转换成一棵二分查找树。其实原理还是跟Convert Sorted Array to Binary Search Tree
这道题相似，我们需要取中点作为当前函数的根。这里的问题是对于一个链表我们是不能常量时间访问它的中间元素的。
这时候就要利用到树的中序遍历了，按照递归中序遍历的顺序对链表结点一个个进行访问，而我们要构造的二分查找树正是按照链表的顺序来的。
思路就是先对左子树进行递归，然后将当前结点作为根，迭代到下一个链表结点，最后在递归求出右子树即可。整体过程就是一次中序遍历，
时间复杂度是O(n)，空间复杂度是栈空间O(logn)
*/

public TreeNode sortedListToBST(ListNode head) {
    if(head == null)
        return null;
    ListNode cur = head;
    
    // count the number of nodes
    int count = 0;
    while(cur!=null)
    {
        cur = cur.next;
        count++;
    }
    
    //define list to hold the head
    ArrayList<ListNode> list = new ArrayList<ListNode>();
    list.add(head);
    
    // call recursive helper function 
    return helper(list,0,count-1);
}

// here the l, and r are not referring anything, purely for counting purpose and ending condition
private TreeNode helper(ArrayList<ListNode> list, int l, int r)
{
    if(l>r)
        return null;
    int m = (l+r)/2;
    
    // the ordering of below is quite important, as the head pointer in parameter list keep changing
    // and the order should be left, root and right. 
    TreeNode left = helper(list,l,m-1);
    TreeNode root = new TreeNode(list.get(0).val);
    root.left = left;
    
    // remember to move head pointer to its next node
    list.set(0,list.get(0).next);
    root.right = helper(list,m+1,r);
    return root;
}

/* another way to use fast and slow pointer to do the tree construction, I prefer this method */

	ListNode cutAtMid(ListNode head) {

        if (head == null) {
            return null;
        }

        ListNode fast = head;
        /* the slow pointer points to the mid node when the fast hit the end of the list */
        ListNode slow = head;
        /* preSlow is to remember the previous node of the slow node, and we need to terminate
           it when fast hit end of the list, so that the head pointer is pointing to first half 
           of the original list, not the whole list */
        ListNode preSlow = head; 

        while (fast != null && fast.next != null) {
            preSlow = slow; // set preSlow to previous slow node before moving slow to its next
            slow = slow.next;
            fast = fast.next.next;
        }

        preSlow.next = null; // this is REALLY IMPORTANT!!!
        return slow;
    }

    public TreeNode sortedListToBST(ListNode head) {

        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return new TreeNode(head.val);
        }

        ListNode mid = cutAtMid(head);

		// the ordering below doesn't matter here
        TreeNode root = new TreeNode(mid.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(mid.next);

        return root;
    }