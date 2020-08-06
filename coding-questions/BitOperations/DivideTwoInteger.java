/* 这道题属于数值处理的题目，对于整数处理的问题，在Reverse Integer中我有提到过，比较重要的注意点在于符号和处理越界的问题。
对于这道题目，因为不能用乘除法和取余运算，我们只能使用位运算和加减法。比较直接的方法是用被除数一直减去除数，直到为0。
这种方法的迭代次数是结果的大小，即比如结果为n，算法复杂度是O(n)。
那么有没有办法优化呢？ 这个我们就得使用位运算。我们知道任何一个整数可以表示成以2的幂为底的一组基的线性组合，
即num=a_0*2^0+a_1*2^1+a_2*2^2+...+a_n*2^n。基于以上这个公式以及左移一位相当于乘以2，我们先让除数左移直到大于被除数之前得到一个最大的基。
然后接下来我们每次尝试减去这个基，如果可以则结果增加加2^k,然后基继续右移迭代，直到基为0为止。因为这个方法的迭代次数是按2的幂直到超过结果，
所以时间复杂度为O(logn)
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
    
    public int divide(int dividend, int divisor) {
    	//if divisor is zero, return Integer.MAX_VALUE
        if(divisor == 0)
        {
            return Integer.MAX_VALUE;
        }
        
        //check the negative flag. >>> is moving with sign bit for signed int. >> is not moving sign bit for signed int
        boolean isNeg = (dividend^divisor)>>>31 == 1;
        
        int res = 0;
        
        /* if dividend is the the smallest integer, we will need to add advisor to dividend, so it will not overflow
           when we take its absolute value later. to compensate, we need to increase res by 1 */
           
        if(dividend == Integer.MIN_VALUE)
        {
            dividend += Math.abs(divisor);
            
            /* if divisor is -1 and dividend is minimum value, we simply return maximum to avoid overflow */
            if(divisor == -1)
            {
                return Integer.MAX_VALUE;
            }
            res++;
        }
        
        /* if divisor is the minimum value, the result should always be zero */
        if(divisor == Integer.MIN_VALUE)
        {
            return res;
        }
        
        /* take absolute value of both dividend and divisor to make the following processing easier */
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        
        /* variable digit is the 2's power times of divisor. ie. digit equaling 2 
        means current divisor is 4 times of its original value */
        int digit = 0;
        
        /* keep times divisor by 2 till it reaches its possible largest */
        while(divisor <= (dividend>>1))
        {
            divisor <<= 1;
            digit++;
            System.out.println(String.format("dividend=%s, divisor=%s, digit=%s, res=%s", dividend, divisor, digit, res));
        }
        
        /* loop backward on digit till it's zero, each time, dividend minus current divisor, 
        adds 2's power of digit to the result, and then decrease digit by 1, and then divides 
        divisor by 2 */
        while(digit>=0)
        {
            if(dividend>=divisor)
            {
                res += 1<<digit;
                dividend -= divisor;
            }
            divisor >>= 1;
            digit--;
            System.out.println(String.format("dividend=%s, divisor=%s, digit=%s, res=%s", dividend, divisor, digit, res));
        }
        return isNeg?-res:res;
    }
    
	public static void main(String[] args) {
	    Main test = new Main();
	    test.divide(-45, 4);
	}
}
