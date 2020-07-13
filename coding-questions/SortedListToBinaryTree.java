/* 这个题是二分查找树的题目，要把一个有序链表转换成一棵二分查找树。其实原理还是跟Convert Sorted Array to Binary Search Tree这道题相似，我们需要取中点作为当前函数的根。这里的问题是对于一个链表我们是不能常量时间访问它的中间元素的。这时候就要利用到树的中序遍历了，按照递归中序遍历的顺序对链表结点一个个进行访问，而我们要构造的二分查找树正是按照链表的顺序来的。思路就是先对左子树进行递归，然后将当前结点作为根，迭代到下一个链表结点，最后在递归求出右子树即可。整体过程就是一次中序遍历，时间复杂度是O(n)，空间复杂度是栈空间O(logn)
*/

public TreeNode sortedListToBST(ListNode head) {
    if(head == null)
        return null;
    ListNode cur = head;
    int count = 0;
    while(cur!=null)
    {
        cur = cur.next;
        count++;
    }
    ArrayList<ListNode> list = new ArrayList<ListNode>();
    list.add(head);
    return helper(list,0,count-1);
}
private TreeNode helper(ArrayList<ListNode> list, int l, int r)
{
    if(l>r)
        return null;
    int m = (l+r)/2;
    TreeNode left = helper(list,l,m-1);
    TreeNode root = new TreeNode(list.get(0).val);
    root.left = left;
    list.set(0,list.get(0).next);
    root.right = helper(list,m+1,r);
    return root;
}