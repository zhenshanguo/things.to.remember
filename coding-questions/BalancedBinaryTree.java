/* 这道题是树操作的题目，还是老套路用递归。这道题是求解树是否平衡，还是有一些小技巧的。要判断树是否平衡，根据题目的定义，深度是比需的信息，所以我们必须维护深度，另一方面我们又要返回是否为平衡树，那么对于左右子树深度差的判断也是必要的。这里我们用一个整数来做返回值，而0或者正数用来表示树的深度，而-1则用来比较此树已经不平衡了，如果已经不平衡，则递归一直返回-1即可，也没有继续比较的必要了，否则就利用返回的深度信息看看左右子树是不是违反平衡条件，如果违反返回-1，否则返回左右子树深度大的加一作为自己的深度即可。算法的时间是一次树的遍历O(n)，空间是栈高度O(logn)。代码如下： */

public boolean isBalanced(TreeNode root)
{
    return helper(root)>=0;
}
private int helper(TreeNode root)
{
    if(root == null)
        return 0;
    int left = helper(root.left);
    int right = helper(root.right);
    if(left<0 || right<0)
        return -1;
    if(Math.abs(left-right)>=2)
        return -1;
    return Math.max(left,right)+1;
}