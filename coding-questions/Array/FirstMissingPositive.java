/*
Given an unsorted integer array, find the first missing positive integer.

For example, Given [1,2,0] return 3, and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.

分析
本质上是桶排序(bucket sort)，每当 A[i]!= i+1 的时候，将A[i]与A[A[i]-1]交换，直到无法交换为止，终止条件是 A[i]== A[A[i]-1]
*/


import java.util.*;

public class Main
{
    public static int firstMissingPositive(int[] nums) {
        bucket_sort(nums);

		// the first index without its nature number occupied, return the missing nature number
        for (int i = 0; i < nums.length; ++i)
            if (nums[i] != (i + 1))
                return i + 1;
        return nums.length + 1;
    }
    
    // move each positive number to its natural position in the array, like 1 should be the first 
    // element with index = 0. for negative numbers or positive numbers bigger than n, ignore it
    private static void bucket_sort(int[] A) {
        final int n = A.length;
        for (int i = 0; i < n; i++) {
            while (A[i] != i + 1) {
                if (A[i] < 1 || A[i] > n || A[i] == A[A[i] - 1])
                    break;
                // swap
                int tmp = A[i];
                A[i] = A[tmp - 1];
                A[tmp - 1] = tmp;
            }
            System.out.println(Arrays.toString(A));
        }
    }
	public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[]{3,4,-1,1}));
	}
}
