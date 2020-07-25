/* 
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
For example,

Consider the following matrix:

[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
Given target = 3, return true
*/

/* this is basically still a variation of binary search, just need to apply it with a 2D array 
   instead of a 1D array, the tricky part is to convert the 1D index into the 2D index */
    
import java.util.Arrays;
import java.lang.Math;

public class Main
{
    public boolean searchMatrix(int[][] matrix, int target) {
        int mx = matrix.length;
        int my = matrix[0].length;
        int l = 0;
        int r = mx * my;
        while (l < r) {
            int m = l + (r - l) / 2;
            // 将m转换成x、y
            int x = m / my;
            int y = m % my;
            // 二分查找：matrix[x][y]转换成一维数组，坐标就是m
            if (matrix[x][y] == target) {
                return true;
            } else if (matrix[x][y] < target) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return false;
    }
    
	public static void main(String[] args) {
	    Main test = new Main();
	    boolean rt = test.searchMatrix(new int[][]{{1,2,4,7},{9,11,19,21},{23,25,27,31},{35,43,48,56}}, 28);
	    System.out.println(rt);
	}
	
}
