/* Follow up for "Remove Duplicates":
What if duplicates are allowed at most twice?

For example,
Given sorted array nums = [1,1,1,2,2,3],Your function should return length = 5, with the first five elements of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave beyond the new length.
*/

import java.util.Arrays;
import java.lang.Math;

public class Main
{
    public int removeDuplicates2(int[] nums) {
        if (nums == null) return 0;
        if (nums.length <=2) return nums.length;
        
        /* start from the third element, and compare each element with its previous 2 elements,
           if all same, ignore current element and move on; otherwise, move the pointer and set
           it with current element */
           
        int len = 2;
        for (int i = 2; i < nums.length; i++) {
            // 一个数字，最多出现2次
            if (!(nums[i] == nums[len - 1] && nums[i] == nums[len - 2])) {
                nums[len++] = nums[i];
            }
        }
        System.out.println(Arrays.toString(nums));
        // in case there is no duplicate, variable len will be bigger than the length, and 
        // we should pick the smaller one
        return Math.min(len, nums.length);
    }
	public static void main(String[] args) {
	    Main test = new Main();
	    test.removeDuplicates2(new int[]{1,1,1,2,2,3,3,3,4,4});
	}
	
}