/* 本质就是用一个栈来模拟递归的过程，但是相比于Binary Tree Inorder Traversal和Binary Tree Preorder Traversal，后序遍历的情况就复杂多了。我们需要维护当前遍历的cur指针和前一个遍历的pre指针来追溯当前的情况（注意这里是遍历的指针，并不是真正按后序访问顺序的结点）。具体分为几种情况：
（1）如果pre的左孩子或者右孩子是cur，那么说明遍历在往下走，按访问顺序继续，即如果有左孩子，则是左孩子进栈，否则如果有右孩子，则是右孩子进栈，如果左右孩子都没有，则说明该结点是叶子，可以直接访问并把结点出栈了。
（2）如果反过来，cur的左孩子是pre，则说明已经在回溯往上走了，但是我们知道后序遍历要左右孩子走完才可以访问自己，所以这里如果有右孩子还需要把右孩子进栈，否则说明已经到自己了，可以访问并且出栈了。
（3）如果cur的右孩子是pre，那么说明左右孩子都访问结束了，可以轮到自己了，访问并且出栈即可。
算法时间复杂度也是O(n)，空间复杂度是栈的大小O(logn) */

import java.util.Arrays;
import java.lang.Math;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;
import java.util.LinkedList;

public class Main
{
    
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root == null)
            return res;
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        TreeNode pre = null;
        while(!stack.isEmpty())
        {
            TreeNode cur = stack.peek();
            System.out.println(String.format("before: cur=%s, pre=%s, stack=%s, res=%s", 
                cur.val, pre==null? "": pre.val, stack, res.toString()));
            if(pre==null || pre.left==cur || pre.right==cur)
            {
                if(cur.left!=null)
                {
                    stack.push(cur.left);
                }
                else if(cur.right!=null)
                {
                    stack.push(cur.right);
                }
                else
                {
                    res.add(cur.val);
                    stack.pop();
                }
            }
            else if(cur.left==pre && cur.right!=null)
            {
                stack.push(cur.right);
            }
            else
            {
                res.add(cur.val);
                stack.pop();
            }
            pre = cur;
            System.out.println(String.format("after: cur=%s, pre=%s, stack=%s, res=%s", 
                cur.val, pre==null? "": pre.val, stack, res.toString()));
        }
        return res;
    }
    
    private static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        
        public TreeNode(int val) {
            this.val = val;
            this.left=null;
            this.right=null;
        }
        
        public String toString() {
            return String.valueOf(this.val);
        }
    }
    
	public static void main(String[] args) {
	    Main test = new Main();
	    TreeNode t1=new TreeNode(1);
	    TreeNode t2=new TreeNode(2);
	    TreeNode t3=new TreeNode(3);
	    TreeNode t4=new TreeNode(4);
	    TreeNode t5=new TreeNode(5);
	    TreeNode t6=new TreeNode(6);
	    TreeNode t7=new TreeNode(7);
	    t1.left=t2;
	    t1.right=t3;
	    t2.left=t4;
	    t2.right=t5;
	    t4.left=t6;
	    t3.right=t7;
	   test.postorderTraversal(t1);
	}
}