/* 
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

click to show follow up.

Follow up:

Did you use extra space?
A straight forward solution using O(_m__n_) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
*/

import java.util.Arrays;
import java.lang.Math;

public class Main
{
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        int mx = matrix.length;
        int my = matrix[0].length;
        // 两个变量，判断第一行和第一列是否有0
        boolean xflag = false, yflag = false;
        // 判断第一行是否有0
        for (int i = 0; i < mx; i++) {
            if (matrix[i][0] == 0) {
                xflag = true;
                break;
            }
        }
        // 判断第一列是否有0
        for (int i = 0; i < my; i++) {
            if (matrix[0][i] == 0) {
                yflag = true;
                break;
            }
        }
        // 其它行、列是否有0
        for (int i = 1; i < mx; i++) {
            for (int j = 1; j < my; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        // 对于第一列，为0，则将所在行变成0
        for (int i = 1; i < mx; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 0; j < my; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 对于第一行，为0，则将所在列变成0
        for (int i = 0; i < my; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 0; j < mx; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        // 若原来第一行中有0，则将整行置0
        if (xflag) {
            for (int i = 0; i < mx; i++) {
                matrix[i][0] = 0;
            }
        }
        // 若原来第一列中有0，则将整列置0
        if (yflag) {
            for (int i = 0; i < my; i++) {
                matrix[0][i] = 0;
            }
        }
        for (int i=0; i<matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

	public static void main(String[] args) {
	    Main test = new Main();
	    test.setZeroes(new int[][]{{1,2,4,7},{5,0,9,11},{3,12,4,7},{5,3,8,0}});
	}
	
}