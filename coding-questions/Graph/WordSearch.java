/* 
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells 
are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
 

Constraints:

board and word consists only of lowercase and uppercase English letters.
1 <= board.length <= 200
1 <= board[i].length <= 200
1 <= word.length <= 10^3
*/

/*
这道题很容易感觉出来是图的题目，其实本质上还是做深度优先搜索。基本思路就是从某一个元素出发，往上下左右深度搜索
是否有相等于word的字符串。这里注意每次从一个元素出发时要重置访问标记（也就是说虽然单次搜索字符不能重复使用，
但是每次从一个新的元素出发，字符还是重新可以用的）。深度优先搜索的算法就不再重复解释了，不了解的朋友可以看看
wiki - 深度优先搜索。我们知道一次搜索的复杂度是O(E+V)，E是边的数量，V是顶点数量，在这个问题中他们都是O(m*n)
量级的（因为一个顶点有固定上下左右四条边）。加上我们对每个顶点都要做一次搜索，所以总的时间复杂度最坏是O(m^2*n^2)，
空间上就是要用一个数组来记录访问情况，所以是O(m*n)。代码如下：
*/

import java.util.Arrays;
import java.lang.Math;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;

public class Main
{
    
    public boolean exist(char[][] board, String word) {
    
    	// edge cases checking
        if(word==null || word.length()==0)
            return true;
        if(board==null || board.length==0 || board[0].length==0)
            return false;
            
        // define a matrix of booleans to track if an element is visited 
        boolean[][] used = new boolean[board.length][board[0].length];
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                if(search(board,word,0,i,j,used))
                    return true;
            }
        }
        return false;
    }
    
    /* recursive way to do DFS search in the matrix. index is the current index of the word
       to be searched in the matrix, (x,y) is the starting point of searching and used is the
       boolean matrix to avoid cycles in searching */
    private boolean search(char[][] board, String word, int index, int i, int j, boolean[][] used)
    {
    	// returning/ending condition
        if(index == word.length()) {
            System.out.println("found it!");
            return true;
        }
        // conditions to stop searching
        if(i<0 || j<0 || i>=board.length || j>=board[0].length || used[i][j] || board[i][j]!=word.charAt(index))
            return false;
        
        // set current element to be visited already before continue searching to left/right/above/below elements
        used[i][j] = true;
        boolean res = search(board,word,index+1,i-1,j,used) 
                    || search(board,word,index+1,i+1,j,used)
                    || search(board,word,index+1,i,j-1,used) 
                    || search(board,word,index+1,i,j+1,used);
        //reset visited flag back to false before return, so that this element can be used in other searches from different starting element
        used[i][j] = false;
        return res;
    }

	public static void main(String[] args) {
	    Main test = new Main();
	    test.exist(new char[][] {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "SEE");
	}
}
