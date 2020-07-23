/* 
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1
*/

import java.util.*;
import java.lang.*;

public class Main
{
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        
        /* find the first number from the second rightest that is smaller than its next to the right 
           this is where we can do swap to get the number serial "bigger". or, we can think it a different
           way, we are looking the first number from the right that decreases, and from its next number to the 
           right end, they are descending */
        int p = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                p = i;
                break;
            }
        }
        
        /* here, we are looking for a number that's just bigger than the number we found above, since
           we search from the right, and numbers from right end to the p+1 are ascending, the first 
           number we get is the smallest but bigger than the number at p, swapping them is correct */
        int q = 0;
        for (int i = nums.length - 1; i > p; i--) {
            if (nums[i] > nums[p]) {
                q = i;
                break;
            }
        }
        
        /* if we can't find the numbers at p and q, they will be both 0, then we will need to reset it 
           to its lowest order */
        if (p == 0 && q == 0) {
            reverse(nums, 0, nums.length - 1);
            return;
        }
        
        /* otherwise, we swap the numbers at p and q, then reverse numbers from p to the end as they are 
           basically still in ascending order from right to left, making them descending will get a lower
           order */
        int temp = nums[p];
        nums[p] = nums[q];
        nums[q] = temp;
        if (p < nums.length - 1) {
            reverse(nums, p + 1, nums.length - 1);
        }
    }
    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

	public static void main(String[] args) {
	    Main test = new Main();
	    int[] nums = new int[] {1,2,4,3,6,9,8,5};
	    test.nextPermutation(nums);
	    System.out.println(Arrays.toString(nums));
	}
}
