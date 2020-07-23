/*这道题是树的题目，其实跟Maximum Depth of Binary Tree非常类似，只是这道题因为是判断最小深度，所以必须增加一个叶子的判断（因为如果一个节点如果只有左子树或者右子树，我们不能取它左右子树中小的作为深度，因为那样会是0，我们只有在叶子节点才能判断深度，而在求最大深度的时候，因为一定会取大的那个，所以不会有这个问题）。这道题同样是递归和非递归的解法，递归解法比较常规的思路，比Maximum Depth of Binary Tree多加一个左右子树的判断，代码如下：*/

public int minDepth(TreeNode root) {
    if(root == null)
        return 0;
    if(root.left == null)
        return minDepth(root.right)+1;
    if(root.right == null)
        return minDepth(root.left)+1;
    return Math.min(minDepth(root.left),minDepth(root.right))+1;
}

/* 非递归解法同样采用层序遍历(相当于图的BFS），只是在检测到第一个叶子的时候就可以返回了，代码如下：  */

public int minDepth(TreeNode root) {
    if(root == null)
        return 0;
    LinkedList queue = new LinkedList();
    int curNum = 0;
    int lastNum = 1;
    int level = 1;
    queue.offer(root);
    while(!queue.isEmpty())
    {
        TreeNode cur = queue.poll();
        if(cur.left==null && cur.right==null)
            return level;
        lastNum--;
        if(cur.left!=null)
        {
            queue.offer(cur.left);
            curNum++;
        }
        if(cur.right!=null)
        {
            queue.offer(cur.right);
            curNum++;
        }
        if(lastNum==0)
        {
            lastNum = curNum;
            curNum = 0;
            level++;
        }
    }
    return 0;
}