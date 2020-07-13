/* Say you have an array for which the ith element is the price of a given stock on day i.
If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit. */

/* 这道题求进行一次交易能得到的最大利润。如果用brute force的解法就是对每组交易都看一下利润，取其中最大的，总用有n*(n-1)/2个可能交易，所以复杂度是O(n^2)。
很容易感觉出来这是动态规划的题目，其实跟Maximum Subarray非常类似，用“局部最优和全局最优解法”。思路是维护两个变量，一个是到目前为止最好的交易，另一个是在当前一天卖出的最佳交易（也就是局部最优）。递推式是local[i+1]=max(local[i]+prices[i+1]-price[i],0), global[i+1]=max(local[i+1],global[i])。这样一次扫描就可以得到结果，时间复杂度是O(n)。而空间只需要两个变量，即O(1)。代码如下
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
    
    public int maxProfit(int[] prices) {
         if(prices==null || prices.length==0)
             return 0;
         int local = 0;
         int global = 0;
         for(int i=1;i<prices.length;i++)
         {
             local = Math.max(local+prices[i]-prices[i-1],prices[i]-prices[i-1]);
             global = Math.max(local, global);
         }
         System.out.println(global);
         return global;
     }
     
     public int maxProfit2(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int maxProfit = 0;
        int lowest = Integer.MAX_VALUE;
        for (int v : prices) {
            lowest = Math.min(v, lowest);
            maxProfit = Math.max(maxProfit, v - lowest);
        }
        return maxProfit;
    }
    
	public static void main(String[] args) {
	    Main test = new Main();
	    test.maxProfit(new int[] {7,1,5,3,6,4});
	}
}