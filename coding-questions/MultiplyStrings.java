/* 这道题属于数值操作的题目，其实更多地是考察乘法运算的本质。基本思路是和加法运算还是近似的，只是进位和结果长度复杂一些。我们仍然是从低位到高位对每一位进行计算，假设第一个数长度是n，第二个数长度是m，我们知道结果长度为m+n或者m+n-1（没有进位的情况）。对于某一位i，要计算这个位上的数字，我们需要对所有能组合出这一位结果的位进行乘法，即第1位和第i位，第2位和第i-1位，... ，然后累加起来，最后我们取个位上的数值，然后剩下的作为进位放到下一轮循环中。这个算法两层循环，每层循环次数是O(m+n)，所以时间复杂度是O((m+n)^2)。算法中不需要额外空间，只需要维护一个进位变量即可，所以空间复杂度是O(1)
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
    
    public String multiply(String num1, String num2) {
        if(num1 == null || num2 == null || num1.length()==0 || num2.length()==0)
            return "";
        if(num1.charAt(0)=='0')
            return "0";
        if(num2.charAt(0)=='0')
            return "0";
        StringBuilder res = new StringBuilder();
        int num = 0;
        for(int i=num1.length()+num2.length();i>0;i--)
        {
            for(int j=Math.min(i-1,num1.length());j>0;j--)
            {
                if(i-j<=num2.length())
                {
                    num += (int)(num1.charAt(j-1)-'0')*(int)(num2.charAt(i-1-j)-'0');
                }
            }
            if(i!=1 || num>0)
                res.append(num%10);
            num = num/10;
        }
        System.out.println(res.reverse().toString());
        return res.reverse().toString();
    }
	public static void main(String[] args) {
	    Main test = new Main();
	    test.multiply("5","72");
	}
}
