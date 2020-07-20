/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/
/* 这道题跟Add Two Numbers很类似，代码结构很接近。从低位开始，一直相加并且维护进位。和Add Two Numbers的区别是这个题目低位在后面，所以要从string的尾部往前加。时间复杂度是O(max(m,n))，m和n分别是两个字符串的长度，空间复杂度是结果的长度O(max(m,n))
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
    
    public String addBinary(String a, String b) {
        if(a==null || a.length()==0)
            return b;
        if(b==null || b.length()==0)
            return a;
        int i=a.length()-1;
        int j=b.length()-1;
        int carry = 0;
        StringBuilder res = new StringBuilder();
        while(i>=0&&j>=0)
        {
            int digit = (int)(a.charAt(i)-'0'+b.charAt(j)-'0')+carry;
            carry = digit/2;
            digit %= 2;
            res.append(digit);
            i--;
            j--;
        }
        while(i>=0)
        {
            int digit = (int)(a.charAt(i)-'0')+carry;
            carry = digit/2;
            digit %= 2;
            res.append(digit);
            i--;
        }
        while(j>=0)
        {
            int digit = (int)(b.charAt(j)-'0')+carry;
            carry = digit/2;
            digit %= 2;
            res.append(digit);
            j--;
        }      
        if(carry>0)
        {
            res.append(carry);
        }
        System.out.println("result: " + res.reverse().toString());
        return res.reverse().toString();
    }

	public static void main(String[] args) {
	    Main test = new Main();
	    test.addBinary("10100100111", "1100101");
	}
}
