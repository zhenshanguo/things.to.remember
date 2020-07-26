/*
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
*/

/*
这道题看似一个关于字符串操作的题目，其实要解决这个问题得用图的方法。我们先给题目进行图的映射，顶点则是每个字
符串，然后两个字符串如果相差一个字符则我们进行连边。接下来看看这个方法的优势，注意到我们的字符集只有小写字母，
而且字符串长度固定，假设是L。那么可以注意到每一个字符可以对应的边则有25个（26个小写字母减去自己），那么一个
字符串可能存在的边是25*L条。接下来就是检测这些边对应的字符串是否在字典里，就可以得到一个完整的图的结构了。
根据题目的要求，等价于求这个图一个顶点到另一个顶点的最短路径，一般我们用广度优先搜索（不熟悉搜索的朋友可以
看看Clone Graph）即可。这个算法中最坏情况是把所有长度为L的字符串都看一下，或者把字典中的字符串都看一下，
而长度为L的字符串总共有26^L，所以时间复杂度是O(min(26^L, size(dict))，空间上需要存储访问情况，
也是O(min(26^L, size(dict))。代码如下：
*/

import java.util.*;
import java.lang.*;

public class Main
{
    // using BFS to find the path length from string start to string end with dict
    public int ladderLength(String start, String end, HashSet<String> dict) {
        
        // check edge cases
        if(start==null || end==null || start.length()==0 || end.length()==0 || start.length()!=end.length())
            return 0;
        //define the queue for BFS
        LinkedList<String> queue = new LinkedList<String>();
        //define a set to keep word visited already to avoid cycle in the search
        HashSet<String> visited = new HashSet<String>();
        
        // typical variables for BFS, level, lastNum and curNum, initialize level to be 1 as the start word counts too
        int level= 1;
        int lastNum = 1;
        int curNum = 0;
        // initialize the queue and the 'visited' set
        queue.offer(start);
        visited.add(start);
        
        while(!queue.isEmpty())
        {
            String cur = queue.poll();
            // after poll from the queue, we need to decrease the number of items in current level, lastNum--
            lastNum--;
            
            // loop through each character in current word, and replace it with a different character from a to z
            for(int i=0;i<cur.length();i++)
            {
                char[] charCur = cur.toCharArray();
                for(char c='a';c<='z';c++)
                {
                    charCur[i] = c;
                    // check if the new string equals string end, if yes return level+1. here we assume the end
                    // string is always in the dict
                    String temp = new String(charCur);
                    if(temp.equals(end))
                        return level+1;
                    // put the new string in the queue and mark it as visited if the new string doesn't match end string, 
                    // and not visited before, also increase the number of items for next level
                    if(dict.contains(temp) && !visited.contains(temp))
                    {
                        curNum++;
                        queue.offer(temp);
                        visited.add(temp);
                    }
                }
            }
            // when we are done with all items in current level, we move to next level
            if(lastNum==0)
            {
                lastNum = curNum;
                curNum = 0;
                level++;
            }
        }
        return 0;
    }

	public static void main(String[] args) {
	    Main test = new Main();
	    int res = test.ladderLength("hit", "cog", new HashSet(Arrays.asList(new String[] {"hot","dot","dog","lot","log","cog"})));
	    System.out.println(res);
	}
}

