/* 这道题要求解一个数字串按照字符串编码方式可解析方式的数量。看到这种求数量的，我们很容易想到动态规划来存储前面信息，然后迭代得到最后结果。
我们维护的量res[i]是表示前i个数字有多少种解析的方式，接下来来想想递归式，有两种方式：第一种新加进来的数字不然就是自己比较表示一个字符，那么解析的方式有res[i-1]种，第二种就是新加进来的数字和前一个数字凑成一个字符，解析的方式有res[i-2]种（因为上一个字符和自己凑成了一个）。当然这里要判断前面说的两种情况能否凑成一个字符，也就是范围的判断，如果可以才有对应的解析方式，如果不行，那么就是0。最终结果就是把这两种情况对应的解析方式相加。这里可以把范围分成几个区间：
（1）00：res[i]=0（无法解析，没有可行解析方式）；
（2）10, 20：res[i]=res[i-2]（只有第二种情况成立）；
（3）11-19, 21-26：res[i]=res[i-1]+res[i-2]（两种情况都可行）；
（4）01-09, 27-99：res[i]=res[i-1]（只有第一种情况可行）；
递推式就是按照上面的规则来得到，接下来我们只要进行一遍扫描，然后依次得到维护量就可以了，算法的时间复杂度是O(n)。空间上可以看出我们每次只需要前两位的历史信息，所以只需要维护三个变量然后迭代赋值就可以了，所以空间复杂度是O(1)
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
    
    public int numDecodings(String s) {
        if(s==null || s.length()==0 || s.charAt(0)=='0')
        {
            return 0;
        }
        int num1=1; //number of decoding at i-2
        int num2=1; //number of decoding at i-1
        int num3=1; //number of decoding at i
        for(int i=1;i<s.length();i++)
        {
            if(s.charAt(i)=='0')
            {
                if(s.charAt(i-1)=='1' || s.charAt(i-1)=='2')
                    num3 = num1;
                else
                    return 0;
            }
            else
            {
                if(s.charAt(i-1)=='0' || s.charAt(i-1)>='3')
                    num3 = num2;
                else
                {
                    if(s.charAt(i-1)=='2' && s.charAt(i)>='7' && s.charAt(i)<='9')
                        num3 = num2;
                    else
                        num3 = num1+num2;
                }
            }
            num1 = num2;
            num2 = num3;
        }
        System.out.println(num2);
        return num2;
    }
    
	public static void main(String[] args) {
	    Main test = new Main();
	    test.numDecodings("123");
	}
}
