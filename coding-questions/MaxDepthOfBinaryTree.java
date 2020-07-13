/* 这是一道比较简单的树的题目，可以有递归和非递归的解法，递归思路简单，返回左子树或者右子树中大的深度加1，作为自己的深度即可，代码如下：*/ 

public int maxDepth(TreeNode root) {
    if(root == null)
        return 0;
    return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
}

/* 非递归解法一般采用层序遍历(相当于图的BFS），因为如果使用其他遍历方式也需要同样的复杂度O(n). 层序遍历理解上直观一些，维护到最后的level便是树的深度。代码如下：  */

public int maxDepth(TreeNode root) {
    if(root == null)
        return 0;
    int level = 0;
    LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
    queue.add(root);
    int curNum = 1; //num of nodes left in current level
    int nextNum = 0; //num of nodes in next level
    while(!queue.isEmpty())
    {
        TreeNode n = queue.poll();
        curNum--;
        if(n.left!=null)
        {
            queue.add(n.left);
            nextNum++;
        }
        if(n.right!=null)
        {
            queue.add(n.right);
            nextNum++;
        }
        if(curNum == 0)
        {
            curNum = nextNum;
            nextNum = 0;
            level++;
        }
    }
    return level;
}