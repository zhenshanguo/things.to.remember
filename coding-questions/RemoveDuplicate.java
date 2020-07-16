/* 
Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

For example,
Given input array nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. It doesn't matter what you leave beyond the new length.
*/

import java.util.Arrays;

public class Main
{
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return -1;
        }
        if (nums.length < 2) {
            return nums.length;
        }
        int len = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[len] != nums[i]) {
                nums[++len] = nums[i];
            }
        }
        System.out.println(Arrays.toString(nums));
        return len + 1; // remember to add 1
    }
	public static void main(String[] args) {
	    Main test = new Main();
	    test.removeDuplicates(new int[]{1,2,2,3,3,4,4});
	}
	
}