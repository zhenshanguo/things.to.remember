/* Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
*/

/* this is another variation of binary search, just with duplicates. in some extreme cases,
   the complexity could be O(n) */
   
import java.util.Arrays;
import java.lang.Math;

public class Main
{
    public int[] searchRange(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] == target) {
                int s = m, e = m;
                while (s - 1 >= 0 && nums[s - 1] == target) {
                    s--;
                }
                while (e + 1 < nums.length && nums[e + 1] == target) {
                    e++;
                }
                return new int[] { s, e };
            } else if (nums[m] > target) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return new int[] { -1, -1 };
    }
    
	public static void main(String[] args) {
	    Main test = new Main();
	    int[] rt = test.searchRange(new int[]{1,2,4,7,9,11,19,21,23,23,23,31,35,43,48,56}, 23);
	    System.out.println(Arrays.toString(rt));
	}
	
}
