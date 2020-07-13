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
        int[][] dp = new int[m][n];
        for (int y = 1; y < n; y++) {
            dp[0][y] = 1;
        }
        for (int x = 1; x < m; x++) {
            dp[x][0] = 1;
        }
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

// to result matrix is in the form of a partial Pascal triangle as the way it calculates each 
// element in the matrix is exactly same as Pascal