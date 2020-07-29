/*
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22
*/

/*
这道题是树操作的题目，判断是否从根到叶子的路径和跟给定sum相同的。还是用常规的递归方法来做，递归条件是看左子树
或者右子树有没有满足条件的路径，也就是子树路径和等于当前sum减去当前节点的值。结束条件是如果当前节点是空的，则
返回false，如果是叶子，那么如果剩余的sum等于当前叶子的值，则找到满足条件的路径，返回true。算法的复杂度是输的
遍历，时间复杂度是O(n)，空间复杂度是O(logn)。
*/

public boolean hasPathSum(TreeNode root, int sum) {
    if(root == null)
        return false;
    if(root.left == null && root.right==null && root.val==sum)
        return true;
    return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);
}