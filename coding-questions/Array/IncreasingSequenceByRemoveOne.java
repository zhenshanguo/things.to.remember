/*
Given a sequence of integers as an array, determine whether it is possible to obtain a strictly increasing sequence by removing no more than one element from the array.
Note: sequence a0, a1, ..., an is considered to be a strictly increasing if a0 < a1 < ... < an. Sequence containing only one element is also considered to be strictly increasing.
Example
For sequence = [1, 3, 2, 1], the output should be
almostIncreasingSequence(sequence) = false.
There is no one element in this array that can be removed in order to get a strictly increasing sequence.
For sequence = [1, 3, 2], the output should be
almostIncreasingSequence(sequence) = true.
You can remove 3 from the array to get the strictly increasing sequence [1, 2]. Alternately, you can remove 2 to get the strictly increasing sequence [1, 3].
Input/Output
[execution time limit] 3 seconds (java)
[input] array.integer sequence
Guaranteed constraints:
2 ≤ sequence.length ≤ 105,
-105 ≤ sequence[i] ≤ 105.
[output] boolean
Return true if it is possible to remove one element from the array in order to get a strictly increasing sequence, otherwise return false.
*/

import java.util.*;

public class Main
{
    public boolean removeOne(int[] num) {
        if (num == null || num.length < 3)
            return true;
        int failCount = 0;
        for (int i = 1; i< num.length; i++) {
            if (num[i] < num[i-1]) {
                if (failCount > 0)
                    return false;
                else 
                    failCount++;
                if (i-2 < 0 || num[i-2] < num[i] || i+1 >= num.length || num[i+1] > num[i-1])
                    continue;
                else 
                    return false;
            }
        }
        return true;
    }

    
	public static void main(String[] args) {
	    Main test = new Main();
	    int[] nums = new int[] {0,1,0,3,2};
	    boolean res = test.removeOne(nums);
	    System.out.println(res);
	}
}
