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
    
    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(root == null)
            return res;
        LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
        while(root!=null || !stack.isEmpty())
        {
            System.out.println(String.format("before: root=%s, stack=%s, res=%s", 
                root == null? "": root.val, stack, res.toString()));
            if(root!=null)
            {
                stack.push(root);
                res.add(root.val);
                root = root.left;
            }
            else
            {
                root = stack.pop();
                root = root.right;
            }
            System.out.println(String.format("after: root=%s, stack=%s, res=%s", 
                root == null? "": root.val, stack, res.toString()));
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
	   test.preorderTraversal(t1);
	}
}
