/* 这是一道比较简单的题目，对一个数组进行加一操作。但是可不要小看这个题，这个题被称为“Google最喜欢的题”，因为在google面试中出现的频率非常高。我们先说说这道题的解法。思路是维护一个进位，对每一位进行加一，然后判断进位，如果有继续到下一位，否则就可以返回了，因为前面不需要计算了。有一个小细节就是如果到了最高位进位仍然存在，那么我们必须重新new一个数组，然后把第一个为赋成1（因为只是加一操作，其余位一定是0，否则不会进最高位）。因为只需要一次扫描，所以算法复杂度是O(n)，n是数组的长度。而空间上，一般情况是O(1)，但是如果数是全9，那么是最坏情况，需要O(n)的额外空间
*/

import java.util.Arrays;
import java.lang.Math;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;
import java.util.LinkedList;

public class Main
{
    
    public int[] plusOne(int[] digits) {
        if(digits == null || digits.length==0)
            return digits;
        int carry = 1;
        for(int i=digits.length-1;i>=0;i--)
        {
            int digit = (digits[i]+carry)%10; 
            carry = (digits[i]+carry)/10;
            digits[i] = digit;
            
            //this is quite efficient, when carry is zero, there will be no chance for carry=1 any more
            if(carry==0) {
                return digits;   
            }
        }
        
        /* only when all digits in the array are 9, we need one more element, so we can do this 
           check at the beginning. below is a smart way to do it, when we need an extra digit, 
           the result must be in the form of 10...0 */
           
        int [] res = new int[digits.length+1];    
        res[0] = 1;
        
        return res;
    }

	public static void main(String[] args) {
	    Main test = new Main();
	    test.plusOne(new int[]{9,9,9,8,9});
	}
}
