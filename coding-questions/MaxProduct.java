/* 这道题跟Maximum Subarray模型上和思路上都比较类似，还是用一维动态规划中的“局部最优和全局最优法”。这里的区别是维护一个局部最优不足以求得后面的全局最优，这是由于乘法的性质不像加法那样，累加结果只要是正的一定是递增，乘法中有可能现在看起来小的一个负数，后面跟另一个负数相乘就会得到最大的乘积。不过事实上也没有麻烦很多，我们只需要在维护一个局部最大的同时，在维护一个局部最小，这样如果下一个元素遇到负数时，就有可能与这个最小相乘得到当前最大的乘积和，这也是利用乘法的性质得到的。代码如下
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
    
    public int maxProduct(int[] A) {
        if(A==null || A.length==0)
            return 0;
        if(A.length == 1)
            return A[0];
        int max_local = A[0];
        int min_local = A[0];
        int global = A[0];
        for(int i=1;i<A.length;i++)
        {
            int max_copy = max_local;
            max_local = Math.max(Math.max(A[i]*max_local, A[i]), A[i]*min_local);
            min_local = Math.min(Math.min(A[i]*max_copy, A[i]), A[i]*min_local);
            global = Math.max(global, max_local);
        }
        System.out.println(global);
        return global;
    }
    
/* Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.

LeetCode第53题Maximum Subarray是求连续和最大的子数组，本题是求连续乘积最大的子数组。

在解法上是一样的，只是在求和时，是负就舍弃。但是在乘积中，因为负数*负数=正数，所以连续乘积为负数时，并不能舍弃这个数，因为如果数组的元素是负数，它可能成为乘积最大的数。

所以LeetCode第53题Maximum Subarray，只需要定义一个变量，用来记录和；本题要定义两个变量：positive和negative，分别记录当前乘积最大值和最小值。*/

    public int maxProduct2(int[] nums) {
        int max = nums[0];
        int positive = nums[0];
        int negative = nums[0];
        for (int i = 1; i < nums.length; i++) {
            positive *= nums[i];
            negative *= nums[i];
            if (positive < negative) {
                int t = positive;
                positive = negative;
                negative = t;
            }
            positive = Math.max(positive, nums[i]);
            negative = Math.min(negative, nums[i]);
            max = Math.max(max, positive);
        }
        return max;
    }
    
	public static void main(String[] args) {
	    Main test = new Main();
	    test.maxProduct(new int[] {-2,1,-3,4,-1,2,1,-5,4,-2});
	}
}
