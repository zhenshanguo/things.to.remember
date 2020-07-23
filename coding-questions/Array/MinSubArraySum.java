/* Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

More practice:

If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
*/

import java.util.*;
import java.lang.*;

public class Main
{
    public int minSubArrayLen(int s, int[] nums) {
        int sum = 0;
        int st = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            /* in case nums[i] is really big, we will keep moving start to right to make the length
        		of the sub array small, and then compare it with the global min. this is kinda DP, we 
        		maintain a local min length, which is i-start+1, and the global min */
            if (sum >= s) {
                while (sum - nums[st] >= s) {
                    sum -= nums[st++];
                }
                min = Math.min(min, i - st + 1);
                System.out.println("min: " + min);
            }
        }
        if (min > nums.length) {
            return 0;
        }
        return min;
    }

	public static void main(String[] args) {
	    Main test = new Main();
	    test.minSubArrayLen(7, new int[] {2,3,1,2,4,3});
	}
}
