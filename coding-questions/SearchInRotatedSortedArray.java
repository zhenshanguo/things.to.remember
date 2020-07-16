/* Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
*/

/* another variation of binary search, the array is still sorted to some extent, just need to 
   do more checking in different cases and locate which half can have the target */
   
import java.util.Arrays;
import java.lang.Math;

public class Main
{
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (nums[m] == target)
                return m;
            if (nums[l] < nums[m]) { 
            // the left half is sorted, so the following check is target smaller than left and bigger than right */ 
                if (target <= nums[m] && target >= nums[l])
                    r = m - 1;
                else
                    l = m + 1;
            } else if (nums[l] > nums[m]) {
            // the left half is zigged, so the checking is bigger than left end OR smaller than right end */
                if (target >= nums[l] || target <= nums[m])
                    r = m - 1;
                else
                    l = m + 1;
            } 
        }
        return -1;
    }
    
	public static void main(String[] args) {
	    Main test = new Main();
	    int rt = test.search(new int[]{31,35,43,48,56,1,2,4,7,9,11,19,21,23}, 9);
	    System.out.println(rt);
	}
	
}
