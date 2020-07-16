/* Given an array and a value, remove all instances of that value in place and return the new length.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.
*/

/* nothing much to say, quite straightforward */
import java.util.*;
import java.lang.*;

public class Main
{
    
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[len++] = nums[i];
            }
        }
        return len;
    }
    
	public static void main(String[] args) {
	    Main test = new Main();
	    test.removeElement(new int[]{1,2,3,4}, 3);
	}
}