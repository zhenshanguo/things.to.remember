import java.util.Arrays;
import java.lang.Math;

public class Main
{
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }
        // 定义三个变量，存储三种颜色出现次数
        int red = 0;
        int white = 0;
        int blue = 0;
        // 循环一次，记录每种颜色出现次数
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                red++;
            } else if (nums[i] == 1) {
                white++;
            } else {
                blue++;
            }
        }
        // 对nums数组重新赋值
        int i = 0;
        while (red-- > 0) {
            nums[i++] = 0;
        }
        while (white-- > 0) {
            nums[i++] = 1;
        }
        while (blue-- > 0) {
            nums[i++] = 2;
        }
        
        System.out.println(Arrays.toString(nums));
    }

	public static void main(String[] args) {
	    Main test = new Main();
	    test.sortColors(new int[]{1,2,0,2,1,0,2,2,0,1,1,2,0});
	}
	
}
