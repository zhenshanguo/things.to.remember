/* 这道题是求解所有可行的二叉查找树，从Unique Binary Search Trees中我们已经知道，可行的二叉查找树的数量是相应的卡特兰数，
不是一个多项式时间的数量级，所以我们要求解所有的树，自然是不能多项式时间内完成的了。算法上还是用求解NP问题的方法来求解，
也就是N-Queens中介绍的在循环中调用递归函数求解子问题。思路是每次一次选取一个结点为根，然后递归求解左右子树的所有结果，
最后根据左右子树的返回的所有子树，依次选取然后接上（每个左边的子树跟所有右边的子树匹配，而每个右边的子树也要跟所有的左边子树
匹配，总共有左右子树数量的乘积种情况），构造好之后作为当前树的结果返回 */

public ArrayList<TreeNode> generateTrees(int n) {
    return helper(1,n);
}
private ArrayList<TreeNode> helper(int left, int right)
{
    ArrayList<TreeNode> res = new ArrayList<TreeNode>();
    
    // define returning condition
    if(left>right)
    {
        res.add(null);
        return res;
    }
    
    // loop through all cases where root is at i
    for(int i=left;i<=right;i++)
    {
    	// find all possible trees with elements from left to i-1
        ArrayList<TreeNode> leftList = helper(left,i-1);
        
        // find all possible trees with elements from i to right
        ArrayList<TreeNode> rightList = helper(i+1,right);
        
        // construct all trees with root at i and add them to the result
        for(int j=0;j<leftList.size();j++)
        {
            for(int k=0;k<rightList.size();k++)
            {
                TreeNode root = new TreeNode(i);
                root.left = leftList.get(j);
                root.right = rightList.get(k);
                res.add(root);
            }
        }
    }
    return res;
}