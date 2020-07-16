/* Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

For example,
Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
*/

// it's NOT easy to come up with the way to bit operation, usually one loop of comparing from 0 through N, which could 
// result in fewer operations in most cases, but bit operations is always n times checking.
import java.util.*;
import java.lang.*;

public class Main
{
    public int missingNumber(int[] nums) {
        int rt = nums.length;
        for (int i=0; i<nums.length; i++) {
            rt = rt^nums[i]^i;
        }
        System.out.println(rt);
        return rt;
    }
    
    public int missingNumber2(int[] nums) {
        for (int i=0; i<nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return nums.length;
    }

	public static void main(String[] args) {
	    Main test = new Main();
	    test.missingNumber(new int[] {0,1,2,3,4,6,7,8});
	}
}