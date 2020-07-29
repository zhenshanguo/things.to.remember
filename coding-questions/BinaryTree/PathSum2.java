/*
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]
*/

/*
这道题是树的题目，跟Path Sum的要求很接近，都是寻找从根到叶子的路径。这道题目的要求是求所有满足条件的路径，所以
我们需要数据结构来维护中间路径结果以及保存满足条件的所有路径。这里的时间复杂度仍然只是一次遍历O(n)，而空间复杂度
则取决于满足条件的路径和的数量（假设是k条），则空间是O(klogn)。代码如下
*/

public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
    // check edge case
    if(root==null)
        return res;
    //define a list to hold a single path
    ArrayList<Integer> item = new ArrayList<Integer>();
    //add current root to the list
    item.add(root.val);
    
    //decrease the sum by current root value and call the recursive function
    helper(root,sum-root.val,item,res);
    return res;
}

// recursive function to find the path that has path sum equal to the target
private void helper(TreeNode root, int sum, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res)
{
	// if root is null return
    if(root == null)
        return;
    //if it's a leaf node and sum is zero, then add the path to result
    if(root.left==null && root.right==null && sum==0)
    {
        res.add(new ArrayList<Integer>(item));
        return;
    }
    // otherwise, if left is not null add root to path, decrease root value and continue search
    // remember when it returned, we need to remove left from the path before searching the right 
    if(root.left!=null)
    {
        item.add(root.left.val);
        helper(root.left,sum-root.left.val,item,res);
        item.remove(item.size()-1);
    }
    //continue to search on right
    if(root.right!=null)
    {
        item.add(root.right.val);
        helper(root.right,sum-root.right.val,item,res);
        item.remove(item.size()-1);
    }        
}