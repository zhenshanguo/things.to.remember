/* 这个题目用到的方法是图形学中的一个常用方法：Flood fill算法，其实就是从一个点出发对周围区域进行目标颜色的填充。
背后的思想就是把一个矩阵看成一个图的结构，每个点看成结点，而边则是他上下左右的相邻点，然后进行一次广度或者深度优先搜索。
接下来我们看看这个题如何用Flood fill算法来解决。首先根据题目要求，边缘上的'O'是不需要填充的，所以我们的办法是对上下左右边缘做Flood fill算法，
把所有边缘上的'O'都替换成另一个字符，比如'#'。接下来我们知道除去被我们换成'#'的那些顶点，剩下的所有'O'都应该被替换成'X'，
而'#'那些最终应该是还原成'O'，如此我们可以做最后一次遍历，然后做相应的字符替换就可以了。复杂度分析上，我们先对边缘做Flood fill算法，
因为只有是'O'才会进行，而且会被替换成'#'，所以每个结点改变次数不会超过一次，因而是O(m*n)的复杂度，最后一次遍历同样是O(m*n)，
所以总的时间复杂度是O(m*n)。空间上就是递归栈（深度优先搜索）或者是队列（广度优先搜索）的空间，同时存在的空间占用不会超过O(m+n)
（以广度优先搜索为例，每次队列中的结点虽然会往四个方向拓展，但是事实上这些结点会有很多重复，假设从中点出发，可以想象最大的扩展不会超过一个菱形，
也就是n/2*2+m/2*2=m+n，所以算法的空间复杂度是O(m+n)）。代码如下：
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
    	
    	// check for edge cases
        if(board==null || board.length<=1 || board[0].length<=1)
            return;
        
        // check the first and last row
        for(int i=0;i<board[0].length;i++)
        {
            fill(board,0,i);
            fill(board,board.length-1,i);
        }
        
        // check the first and last column
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
        int code = i*board[0].length+j; // using code so we dont need to save x and y
        queue.offer(code);
        while(!queue.isEmpty())
        {
            code = queue.poll();
            
            // decoding back to x and y
            int row = code/board[0].length;
            int col = code%board[0].length;
            
            // check the element above current element, if zero, set it to # and push it to the queue
            if(row>0 && board[row-1][col]=='O')
            {
                queue.offer((row-1)*board[0].length+col);
                board[row-1][col]='#';
            }
            
            // check the element below current element, if zero, set it to # and push it to the queue
            if(row<board.length-1 && board[row+1][col]=='O')
            {
                queue.offer((row+1)*board[0].length+col);
                board[row+1][col]='#';
            }
            
            // check the element to the left of current element, if zero, set it to # and push it to the queue
            if(col>0 && board[row][col-1]=='O')
            {
                queue.offer(row*board[0].length+col-1);
                board[row][col-1]='#';
            }
            
            // check the element to the right of current element, if zero, set it to # and push it to the queue
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