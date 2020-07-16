/* Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.

[show hint]

Hint:
Could you do it in-place with O(1) extra space?

Related problem: Reverse Words in a String II
*/

/* not easy to come up with the 'reverse' way to do it in place, need to find out the pattern */

import java.util.Arrays;
import java.lang.Math;

public class Main
{
    void reverse(int[] nums, int st, int ed) {
        while (st < ed) {
            int t = nums[st];
            nums[st] = nums[ed];
            nums[ed] = t;
            st++;
            ed--;
        }
    }
    public void rotate(int[] nums, int k) {
        int length = nums.length;
        k = k % length;
        if (length == 1 || k == 0)
            return;
        reverse(nums, 0, length - k - 1);
        reverse(nums, length - k, length - 1);
        reverse(nums, 0, length - 1);
        System.out.println(Arrays.toString(nums));
    }
	public static void main(String[] args) {
	    Main test = new Main();
	    test.rotate(new int[]{1,2,4,7,5,6,9,11,3,12}, 4);
	}
	
}