/*
Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note:
You are not suppose to use the library's sort function for this problem.

click to show follow up.

Follow up:
A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.

Could you come up with an one-pass algorithm using only constant space?
*/

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
