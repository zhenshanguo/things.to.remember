/*
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.
*/

import java.util.*;
import java.lang.*;

public class Main
{
    public void moveZeroes(int[] nums) {
        int t = 0;
        // 把非0元素移到前面, not very straightforward 
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[t++] = nums[i];
            }
        }
        // 把后面元素值0
        for (int i = t; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

	public static void main(String[] args) {
	    Main test = new Main();
	    int[] nums = new int[] {0,1,0,3,0,6,7,0};
	    test.moveZeroes(nums);
	    System.out.println(Arrays.toString(nums));
	}
}