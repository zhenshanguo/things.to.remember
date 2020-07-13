import java.util.Arrays;

public class Main
{
    public int[] productExceptSelf(int[] nums) {
        int[] rt = new int[nums.length];
        rt[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            rt[i] = rt[i + 1] * nums[i+1];
            System.out.println(Arrays.toString(rt));
        }
        int left = 1;
        for (int i = 0; i < nums.length; i++) {
            rt[i] *= left;
            left *= nums[i];
            System.out.println(Arrays.toString(rt));
        }
        return rt;
    }
	public static void main(String[] args) {
	    Main test = new Main();
	    test.productExceptSelf(new int[]{1,2,3,4});
	    test.easyOne(new int[]{1,2,3,4});
	}
	
	public int[] easyOne(int[] nums) {
	    int total = 1;
	    int[] rt = new int[nums.length];
	    for (int i=0; i< nums.length; i++) {
	        total *= nums[i];
	    }
	   
	    for (int i= 0; i<nums.length; i++) {
	        rt[i] = total/nums[i];
	    }
	    System.out.println(Arrays.toString(rt));
	    return rt;
	}
}
