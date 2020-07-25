/* 
Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
*/

import java.util.Arrays;
import java.lang.Math;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Main
{
    public List<String> summaryRanges(int[] nums) {
        List<String> rt = new ArrayList<String>();
        
        // check edge case
        if (nums == null || nums.length == 0) {
            return rt;
        }
        
        // one-pass loop, we maintain 2 variables, one is range start, and the other is range end
        for (int i = 0; i < nums.length; i++) {
        
        	//reset the start element and end element to current element in array
            int st = nums[i];
            int ed = st;
            
            //searching continuous element starting from index i, and update index ed and i
            while (i + 1 < nums.length && nums[i + 1] - nums[i] == 1) {
                i++;
                ed++;
            }
            if (ed == st) {
                rt.add(st + "");
            } else {
                rt.add(st + "->" + ed);
            }
        }
        System.out.println(rt.toString());
        return rt;
    }
    
	public static void main(String[] args) {
	    Main test = new Main();
	    test.summaryRanges(new int[]{1,2,3,3,4,5,7,8,9});
	}
	
}
