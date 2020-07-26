/*
这道题是检查一颗二分查找树是否合法，二分查找树是非常常见的一种数据结构，因为它可以在O(logn)时间内实现搜索。这里我们介绍两种方法来解决这个问题。
第一种是利用二分查找树的性质，就是它的中序遍历结果是按顺序递增的。根据这一点我们只需要中序遍历这棵树，然后保存前驱结点，每次检测是否满足递增关
系即可。注意以下代码我怎么用一个一个变量的数组去保存前驱结点，原因是java没有传引用的概念，如果传入一个变量，它是按值传递的，所以是一个备份的变量，
改变它的值并不能影响它在函数外部的值，算是java中的一个小细节
*/

public boolean isValidBST1(TreeNode root) {
    ArrayList<Integer> pre = new ArrayList<Integer>();
    pre.add(null);
    return helper(root, pre);
}
private boolean helper1(TreeNode root, ArrayList<Integer> pre)
{
    if(root == null)
        return true;
    boolean left = helper(root.left,pre);
    if(pre.get(0)!=null && root.val<=pre.get(0))
        return false;
    pre.set(0,root.val);
    return left && helper(root.right,pre);
}

/*
第二种方法是根据题目中的定义来实现，其实就是对于每个结点保存左右界，也就是保证结点满足它的左子树的每个结点比当前结点值小，右子树的每个结点比当前结点值大。对于根节点不用定位界，所以是无穷小到无穷大，接下来当我们往左边走时，上界就变成当前结点的值，下界不变，而往右边走时，下界则变成当前结点值，上界不变。如果在递归中遇到结点值超越了自己的上下界，则返回false，否则返回左右子树的结果
*/

public boolean isValidBST2(TreeNode root) {
    return helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE); 
}
boolean helper2(TreeNode root, int min, int max)   
{  
    if(root == null)  
       return true;  
    if(root.val <= min || root.val >= max)
         return false;  
     return helper(root.left, min, root.val) && helper(root.right, root.val, max);
} 

/*上述两种方法本质上都是做一次树的遍历，所以时间复杂度是O(n)，空间复杂度是O(logn)。个人其实更喜欢第一种做法，因为思路简单，而且不需要用到整数最大值和最小值这种跟语言相关的量来定义无穷大和无穷小。*/