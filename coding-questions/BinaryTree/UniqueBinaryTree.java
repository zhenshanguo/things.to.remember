/* 这道题要求可行的二叉查找树的数量，其实二叉查找树可以任意取根，只要满足中序遍历有序的要求就可以。从处理子问题的角度来看，
选取一个结点为根，就把结点切成左右子树，以这个结点为根的可行二叉树数量就是左右子树可行二叉树数量的乘积，所以总的数量是将以
所有结点为根的可行结果累加起来。写成表达式如下：熟悉卡特兰数的朋友可能已经发现了，这正是卡特兰数的一种定义方式，是一个典型
的动态规划的定义方式（根据其实条件和递推式求解结果）。所以思路也很明确了，维护量res[i]表示含有i个结点的二叉查找树的数量。
根据上述递推式依次求出1到n的的结果即可。时间上每次求解i个结点的二叉查找树数量的需要一个i步的循环，总体要求n次，所以总时间
复杂度是O(1+2+...+n)=O(n^2)。空间上需要一个数组来维护，并且需要前i个的所有信息，所以是O(n) */

import java.util.*;
import java.lang.*;

public class Main
{
    /* recursive way to do the calculation following the comments above, complexity is O(n^2), and O(logn) space */
    public int numTrees2(int n) {
        if (n<=1)
            return 1;
        int res = 0;
        for (int i=1; i<=n; i++) {
        	// add the number of trees to result when root at number i
            res += numTrees2(i-1) * numTrees2(n-i);
        }
        return res;
    }
    
    /* DP way to do the calculation, which takes O(n) extra space, and complexity is O(n^2) */
    public int numTrees(int n) {
        if(n<=0)
            return 0;
        int[] res = new int[n+1];
        res[0] = 1;
        res[1] = 1;
        for(int i=2;i<=n;i++)
        {
        	/* the reason the ending condition is j<i, instead of j<=i is that the root takes 1 number
        	   so, the total number shared by left and right is i-1 */
            for(int j=0;j<i;j++)
            {
                res[i] += res[j]*res[i-j-1];
            }
        }
        return res[n];
    }

	public static void main(String[] args) {
	    Main test = new Main();
	    int res = test.numTrees2(5);
	    System.out.println(res);
	}
}
/* 这种求数量的题目一般都容易想到用动态规划的解法，这道题的模型正好是卡特兰数的定义。当然这道题还可以用卡特兰数的通项公式来求解，
这样时间复杂度就可以降低到O(n)*/