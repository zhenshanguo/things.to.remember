import java.util.Arrays;
import java.lang.Math;

public class Main
{
    public int removeDuplicates2(int[] nums) {
        if (nums == null) return 0;
        if (nums.length <=2) return nums.length;
        
        int len = 2;
        for (int i = 2; i < nums.length; i++) {
            // 一个数字，最多出现2次
            if (!(nums[i] == nums[len - 1] && nums[i] == nums[len - 2])) {
                nums[len++] = nums[i];
            }
        }
        System.out.println(Arrays.toString(nums));
        return Math.min(len, nums.length);
    }
	public static void main(String[] args) {
	    Main test = new Main();
	    test.removeDuplicates2(new int[]{1,1,1,2,2,3,3,3,4,4});
	}
	
}