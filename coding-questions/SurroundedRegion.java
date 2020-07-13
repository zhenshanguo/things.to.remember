/* 这道题是要求出最长的整数连续串。我们先说说简单直接的思路，就是先排序，然后做一次扫描，记录当前连续串长度，如果连续串中断，则比较是否为当前最长连续串，并且把当前串长度置0。这样时间复杂度是很明确，就是排序的复杂度加上一次线性扫描。如果不用特殊的线性排序算法，复杂度就是O(nlogn)。
其实这个题看起来是数字处理，排序的问题，但是如果要达到好的时间复杂度，还得从图的角度来考虑。思路是把这些数字看成图的顶点，而边就是他相邻的数字，然后进行深度优先搜索。通俗一点说就是先把数字放到一个集合中，拿到一个数字，就往其两边搜索，得到包含这个数字的最长串，并且把用过的数字从集合中移除（因为连续的关系，一个数字不会出现在两个串中）。最后比较当前串是不是比当前最大串要长，是则更新。如此继续直到集合为空。如果我们用HashSet来存储数字，则可以认为访问时间是常量的，那么算法需要一次扫描来建立集合，第二次扫描来找出最长串，所以复杂度是O(2*n)=O(n)，空间复杂度是集合的大小，即O(n)
————————————————
版权声明：本文为CSDN博主「Code_Ganker」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/linhuanmars/article/details/22964467
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
    
    public void solve(char[][] board) {
        if(board==null || board.length<=1 || board[0].length<=1)
            return;
        for(int i=0;i<board[0].length;i++)
        {
            fill(board,0,i);
            fill(board,board.length-1,i);
        }
        for(int i=0;i<board.length;i++)
        {
            fill(board,i,0);
            fill(board,i,board[0].length-1);
        }
        
        //after flooding above, all 'O' left are those surrounded by 'X', and can 
        //be changed to 'X', those '#' are either on border or adjacent to border
        //so they should be changed back to 'O'
        for(int i=0;i<board.length;i++)
        {
            for(int j=0;j<board[0].length;j++)
            {
                if(board[i][j]=='O')
                    board[i][j]='X';
                else if(board[i][j]=='#')
                    board[i][j]='O';                
            }
        }
        for (int i=0; i<board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }
    
    //pick one element and start searching around it and update to # for 'O', also for 
    // newly found '0', put it into queue to be checked later, and the flooding starts
    private void fill(char[][] board, int i, int j)
    {
        if(board[i][j]!='O')
            return;
        board[i][j] = '#';
        LinkedList<Integer> queue = new LinkedList<Integer>();
        int code = i*board[0].length+j;
        queue.offer(code);
        while(!queue.isEmpty())
        {
            code = queue.poll();
            int row = code/board[0].length;
            int col = code%board[0].length;
            if(row>0 && board[row-1][col]=='O')
            {
                queue.offer((row-1)*board[0].length+col);
                board[row-1][col]='#';
            }
            if(row<board.length-1 && board[row+1][col]=='O')
            {
                queue.offer((row+1)*board[0].length+col);
                board[row+1][col]='#';
            }
            if(col>0 && board[row][col-1]=='O')
            {
                queue.offer(row*board[0].length+col-1);
                board[row][col-1]='#';
            }
            if(col<board[0].length-1 && board[row][col+1]=='O')
            {
                queue.offer(row*board[0].length+col+1);
                board[row][col+1]='#';
            }            
        }
    }

	public static void main(String[] args) {
	    Main test = new Main();
	    test.solve(new char[][] {{'X','X','O','X'},{'X','X','X','O'},{'X','X','O','X'},{'O','X','X','X'}});
	}
}