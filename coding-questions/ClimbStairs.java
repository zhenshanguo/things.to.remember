/* 这道题目是求跑楼梯的可行解法数量。每一步可以爬一格或者两个楼梯，可以发现，递推式是f(n)=f(n-1)+f(n-2)，也就是等于前一格的可行数量加上前两格的可行数量。熟悉的朋友可能发现了，这个递归式正是斐波那契数列的定义，不熟悉的朋友可以看看Wiki - 斐波那契数列。根据这个定义，其实很容易实现，可以用递归或者递推都是比较简单的，下面列举一下递推的代码
*/

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
    
    public int climbStairs(int n) {
        int f1 = 1;
        int f2 = 2;
        if(n==1)
            return f1;
        if(n==2)
            return f2;
        for(int i=3;i<=n;i++)
        {
            int f3 = f1+f2;
            f1 = f2;
            f2 = f3;
        }
        System.out.println(f2);
        return f2;
    }
    
	public static void main(String[] args) {
	    Main test = new Main();
	    test.climbStairs(8);
	}
}
