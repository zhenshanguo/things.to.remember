/*
Candy
描述
There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?

分析
无

迭代版
*/

import java.util.*;

public class Main
{
    public int candy(int[] ratings) {
        final int n = ratings.length;
        final int[] increment = new int[n];

        // 左右各扫描一遍
        for (int i = 1, inc = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1])
                increment[i] = Math.max(inc++, increment[i]);
            else // when current rating is smaller than its left reset inc to 1
                inc = 1;
        }

        for (int i = n - 2, inc = 1; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1])
                increment[i] = Math.max(inc++, increment[i]);
            else
                inc = 1;
        }
        // 初始值为n，因为每个小朋友至少一颗糖
        int sum = n;
        for (int i : increment) sum += i;
        System.out.println(Arrays.toString(increment));
        return sum;
    }
    
    /* a recursive way to solve it */
    public int candy2(int[] ratings) {
        final int[] f = new int[ratings.length];
        int sum = 0;
        for (int i = 0; i < ratings.length; ++i)
            sum += solve(ratings, f, i);
        return sum;
    }
    int solve(int[] ratings, int[] f, int i) {
        if (f[i] == 0) {
            f[i] = 1; // if its zero, initialize it to be 1
            //checking neighbors on left and right, and pick the bigger increment
            if (i > 0 && ratings[i] > ratings[i - 1])
                f[i] = Math.max(f[i], solve(ratings, f, i - 1) + 1);
            if (i < ratings.length - 1 && ratings[i] > ratings[i + 1])
                f[i] = Math.max(f[i], solve(ratings, f, i + 1) + 1);
        }
        return f[i];
    }
    
	public static void main(String[] args) {
	    Main test = new Main();
	    int res = test.candy2(new int[] {2,1,4,3,6,5,6,6,7,4,6});
	    System.out.println(res);
	}
}
