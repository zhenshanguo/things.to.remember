/* Given a set of distinct integers, nums, return all possible subsets.

Note:

Elements in a subset must be in non-descending order.
The solution set must not contain duplicate subsets.
For example,
If nums = [1,2,3], a solution is:

[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
*/

/* 
 this problem can also be solved using binary instead of recursive way. one bit for each number in the array. 
 1 means the number is selected in the subset, and 0 means not select. there are totally 2^n ways
 if there is no duplicate numbers in the array

 when there are duplicate numbers, we will need to rely on set to eliminate duplicates 
*/
 
import java.util.Arrays;
import java.lang.Math;
import java.util.List;
import java.util.ArrayList;

public class Main
{
    int target;// 次数
    Integer[] stack;// 存储每次排列
    List<List<Integer>> rt;// 存储结果
    
    public void search(int p, int[] nums) {
        // if current length p, equals to target length, target, we add current stack to result list
        if (p == target) {
            rt.add(new ArrayList<Integer>(Arrays.asList(stack)));
            System.out.println(Arrays.toString(stack));
            return;
        }
        
        // go through all element in the array
        for (int i = 0; i < nums.length; i++) {
        // when there are at least one number in current stack, we need to make sure we only select 
        // numbers that are bigger than the largest number in current stack to ensure the ascending order
            if (p > 0 && nums[i] <= stack[p - 1]) {
                continue;
            }
            stack[p] = nums[i];
            search(p + 1, nums);
        }
        
    }
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        rt = new ArrayList<List<Integer>>();
        // 分别做0~num.length长度的组合
        for (int i = 0; i <= nums.length; i++) {
            target = i;
            stack = new Integer[i];
            search(0, nums);
        }
        return rt;
    }
    
	public static void main(String[] args) {
	    Main test = new Main();
	    test.subsets(new int[]{1,2,4,5});
	}
	
}

