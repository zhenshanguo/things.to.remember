/* 
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?



Above is a 3 x 7 grid. How many possible unique paths are there?

Note: m and n will be at most 100.
*/

/*
   the result matrix is in the form of a partial Pascal triangle as the way it calculates each 
   element in the matrix is exactly the same as Pascal
*/

/* this is a typical DP problem, to get to element [x][y], there are only '2' paths, one is through
   [x-1][y], and the other is through [x][y-1], so paths[x][y] = paths[x-1][y] + paths[x][y-1], and we 
   can initialize the first row and column with all 1, then calculate the rest based on the formula above
*/

import java.util.Arrays;
import java.lang.Math;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class Main
{
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0) {
            return 0;
        }
        
        // initialize all element to be zero, by default
        int[][] dp = new int[m][n];
        
        //initialize the first row
        for (int y = 1; y < n; y++) {
            dp[0][y] = 1;
        }
        
        // initialize the first column
        for (int x = 1; x < m; x++) {
            dp[x][0] = 1;
        }
        
        //calculate the rest of element in the result matrix
        for (int y = 1; y < n; y++) {
            for (int x = 1; x < m; x++) {
                dp[x][y] = dp[x - 1][y] + dp[x][y - 1];
            }
        }
        for (int i= 0; i< m; i++)
            System.out.println(Arrays.toString(dp[i]));
        return dp[m - 1][n - 1];
    }
    
	public static void main(String[] args) {
	    Main test = new Main();
	    test.uniquePaths(4,4);
	}
}

