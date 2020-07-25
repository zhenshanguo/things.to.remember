/* 这道题是树的题目，属于最基本的树遍历的问题。问题要求就是判断两个树是不是一样，基于先序，中序或者后序遍历都可以做完成，因为对遍历顺序没有要求。这里我们主要考虑一下结束条件，如果两个结点都是null，也就是到头了，那么返回true。如果其中一个是null，说明在一棵树上结点到头，另一棵树结点还没结束，即树不相同，或者两个结点都非空，并且结点值不相同，返回false。最后递归处理两个结点的左右子树，返回左右子树递归的与结果即可。这里使用的是先序遍历，算法的复杂度跟遍历是一致的，如果使用递归，时间复杂度是O(n)，空间复杂度是O(logn)
*/ 

public boolean isSameTree(TreeNode p, TreeNode q) {
    if(p==null && q==null)
        return true;
    if(p==null || q==null)
        return false;
    if(p.val!=q.val)
        return false;
    return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
}