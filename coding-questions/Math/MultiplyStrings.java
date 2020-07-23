/*
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Example 1:

Input: num1 = "2", num2 = "3"
Output: "6"
Example 2:

Input: num1 = "123", num2 = "456"
Output: "56088"
Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contain only digits 0-9.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
You must not use any built-in BigInteger library or convert the inputs to integer directly.
*/

/* 这道题属于数值操作的题目，其实更多地是考察乘法运算的本质。基本思路是和加法运算还是近似的，只是进位和结果长度复杂一些。
我们仍然是从低位到高位对每一位进行计算，假设第一个数长度是n，第二个数长度是m，我们知道结果长度为m+n或者m+n-1（没有进位的情况）。
对于某一位i，要计算这个位上的数字，我们需要对所有能组合出这一位结果的位进行乘法，即第1位和第i位，第2位和第i-1位，... ，然后累加起来，
最后我们取个位上的数值，然后剩下的作为进位放到下一轮循环中。这个算法两层循环，每层循环次数是O(m+n)，所以时间复杂度是O((m+n)^2)。
算法中不需要额外空间，只需要维护一个进位变量即可，所以空间复杂度是O(1)
*/

import java.util.*;
import java.lang.*;

public class Main
{
    
    public String multiply(String num1, String num2) {
        // checking for edge cases
        if(num1 == null || num2 == null || num1.length()==0 || num2.length()==0)
            return "";
        if(num1.charAt(0)=='0')
            return "0";
        if(num2.charAt(0)=='0')
            return "0";
            
        int m = num1.length(), n=num2.length();
        StringBuilder res = new StringBuilder();
        int num = 0;
        
        /* here i is not the index of an array, it's just like a counter of number of digits of the result
           or the natural index (starting with 1) of the digit, we first assume there are (m+n) digits in result 
           we calculate from the first digit */
        for(int r=1; r<=m+n; r++)
        {
        	/* i is the 'natural' index of digit from array num1. and j is the natural index of num2, still they
        	   are not array index, they are the digit index starting from 1 */
            for(int i=1; i<=Math.min(r, m); i++)
            {
                // i+j = r+1
                int j = r + 1 - i;
                System.out.println(String.format("i=%s, j=%s, i-j=%s", i, j, i-j));
                if(j<=n)
                {
                    // here we need to convert the natural digit index to array index
                    num += (int)(num1.charAt(m -i)-'0')*(int)(num2.charAt(n-j)-'0');
                }
                System.out.println(String.format("num=%s, res=%s", num, res.toString()));
            }
            if(r!=m + n || num>0)
                res.append(num%10);
            num = num/10;
        }
        /* the reason we reveres the string is because we calculate the last digit first */
        return res.reverse().toString();
    }
	public static void main(String[] args) {
	    Main test = new Main();
	    String res = test.multiply("456","9987");
	    System.out.println(res);
	}
}
