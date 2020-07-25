/*
Given an integer n, generate a square matrix filled with elements from 1 to n^2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:

[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
*/

import java.util.Arrays;
import java.lang.Math;
import java.util.List;
import java.util.ArrayList;

public class Main
{
    public int[][] generateMatrix(int n) {
        if (n <= 0) {
            return new int[0][0];
        }
        int[][] matrix = new int[n][n];
        int num = 1;
        int startx = 0, endx = n - 1;
        int starty = 0, endy = n - 1;
        while (startx <= endx && starty <= endy) {
            // 上边的行，从左向右
            for (int y = starty; y <= endy; y++) {
                matrix[startx][y] = num++;
            }
            // 右边的列，从上到下
            for (int x = startx + 1; x <= endx; x++) {
                matrix[x][endy] = num++;
            }
            // 如果行或列遍历完，则退出循环
            /* not really necessary */
            if (startx == endx || starty == endy) {
                break;
            }
            // 下边的行，从右向左
            for (int y = endy - 1; y >= starty; y--) {
                matrix[endx][y] = num++;
            }
            // 左边的列，从下到上
            for (int x = endx - 1; x >= startx + 1; x--) {
                matrix[x][starty] = num++;
            }
            startx++;
            starty++;
            endx--;
            endy--;
        }
        for (int i=0; i<matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        return matrix;
    }
	public static void main(String[] args) {
	    Main test = new Main();
	    test.generateMatrix(7);
	}
	
}
