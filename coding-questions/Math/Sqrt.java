/*
Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:

Input: 4
Output: 2
Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.
*/

/*
这是一道数值处理的题目，和Divide Two Integers不同，这道题一般采用数值中经常用的另一种方法：二分法。基本思路
是跟二分查找类似，要求是知道结果的范围，取定左界和右界，然后每次砍掉不满足条件的一半，知道左界和右界相遇。算法的
时间复杂度是O(logx)，空间复杂度是O(1)。代码如下：
*/
import java.util.*;
import java.lang.*;

public class Main
{
    // use binary search for the sqrt root, the crucial part is to define the boundary
    public int sqrt(int x) {
        // checking for edge cases
        if(x<0) return -1;
        if(x==0) return 0;
        //define the left and right boundary
        int l=1;
        // this is not easy to come up with
        int r=x/2+1;
        while(l<=r)
        {
            int m = (l+r)/2;
            // conditions to return
            if(m<=x/m && x/(m+1)<m+1)
                return m;
            if(x/m<m)
            {
                r = m-1;
            }
            else
            {
                l = m+1;
            }
        }
        return 0;
    }

	public static void main(String[] args) {
	    Main test = new Main();
	    int res = test.sqrt(0);
	    System.out.println(res);
	}
}
