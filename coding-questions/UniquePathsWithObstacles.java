/* 
Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,

There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Note: m and n will be at most 100.
*/

/* 和上一题一样，只是有障碍物的时候（obstacleGrid[i][j]==1），将对应的dp置0（dp[i][j]=0）*/

import java.util.Arrays;
import java.lang.Math;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Main
{
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    
    	// check edge cases
        if (obstacleGrid == null || obstacleGrid[0] == null) {
            return 0;
        }
        //another edge case, easy to ignore!
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        //define the result and initialize all elements to be zero, by default
        int[][] dp = new int[m][n];
        
        //initialize the first row, if current element is not zero, set the rest to be 0 in the result as they are all blocked
        for (int y = 1; y < n; y++) {
            if (obstacleGrid[0][y] == 0) {
                dp[0][y] = 1;
            } else {
                break;
            }
        }
        
        // initialize the first column, if current element is not zero, set the rest to be 0 in the result as they are all blocked
        for (int x = 1; x < m; x++) {
            if (obstacleGrid[x][0] == 0) {
                dp[x][0] = 1;
            } else {
                break;
            }
        }
        
        // calculate the rest elements in the result, REMEMBER to check if current element in the input array is 1, if yes, set 
        // current element in the result to be 0; otherwise, follow the formula
        for (int y = 1; y < n; y++) {
            for (int x = 1; x < m; x++) {
                if (obstacleGrid[x][y] == 1) {
                    dp[x][y] = 0;
                } else {
                    dp[x][y] = dp[x - 1][y] + dp[x][y - 1];
                }
            }
        }
        for (int i=0; i< dp.length; i++)
            System.out.println(Arrays.toString(dp[i]));
        return dp[m - 1][n - 1];
    }
    
	public static void main(String[] args) {
	    Main test = new Main();
	    test.uniquePathsWithObstacles(new int[][]{{0,0,1,0},{0,0,0,1},{0,0,0,0},{1,0,0,0}});
	}
	
}
