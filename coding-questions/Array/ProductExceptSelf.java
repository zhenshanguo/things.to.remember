/* Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6].
*/

/* the algorithm is quite smart, current element equals the product of all elements of its right
   times the product of all elements of its left */
   
import java.util.Arrays;

public class Main
{
    public int[] productExceptSelf(int[] nums) {
        int[] rt = new int[nums.length];
        
        /* using a variable to track the product of all elements to the right of current element */
        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            rt[i] = right; // here, current element equals products of all element ot its right
            right *= nums[i];
            System.out.println(Arrays.toString(rt));
        }
        
        /* using a variable to track the product of all elements to the left of current element */
        int left = 1;
        for (int i = 0; i < nums.length; i++) {
            rt[i] *= left; // here, we need to 
            left *= nums[i];
            System.out.println(Arrays.toString(rt));
        }
        return rt;
    }
    
    public int[] productExceptSelf2(int[] nums) {
        final int[] result = new int[nums.length];
        final int[] left = new int[nums.length];
        final int[] right = new int[nums.length];

        left[0] = 1;
        right[nums.length - 1] = 1;

        for (int i = 1; i < nums.length; ++i) {
            left[i] = nums[i - 1] * left[i - 1];
        }

        for (int i = nums.length - 2; i >= 0; --i) {
            right[i] = nums[i + 1] * right[i + 1];
        }

        for (int i = 0; i < nums.length; ++i) {
            result[i] = left[i] * right[i];
        }

        return result;
    }

	public static void main(String[] args) {
	    Main test = new Main();
	    test.productExceptSelf(new int[]{1,2,3,4});
	}
}
