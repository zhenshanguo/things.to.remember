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