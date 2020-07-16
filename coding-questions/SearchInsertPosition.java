/*
Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0
*/

import java.util.Arrays;
import java.lang.Math;

public class Main
{
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // the first element that not smaller than the target will be the result
        for (int i = 0; i < nums.length; i++) {
            if (target <= nums[i]) {
                return i;
            }
        }
        return nums.length;
    }
    
	public static void main(String[] args) {
	    Main test = new Main();
	    int rt = test.searchInsert(new int[]{1,2,4,7,9,11,19,21,23,31,35,43,48,56}, 20);
	    System.out.println(rt);
	}
}