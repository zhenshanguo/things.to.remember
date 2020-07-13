import java.util.Arrays;

public class Main
{
    public int removeDuplicates(int[] nums) {
        if (nums == null) {
            return -1;
        }
        if (nums.length < 2) {
            return nums.length;
        }
        int len = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[len] != nums[i]) {
                nums[++len] = nums[i];
            }
        }
        System.out.println(Arrays.toString(nums));
        return len + 1;
    }
	public static void main(String[] args) {
	    Main test = new Main();
	    test.removeDuplicates(new int[]{1,2,2,3,3,4,4});
	}
	
}