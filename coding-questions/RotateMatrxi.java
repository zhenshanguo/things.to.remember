/* You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Follow up:
Could you do this in-place?
*/

/* you can do the rotating layer by layer from outer in, but it will be slow. an efficient 
	way is to do some mirroring along some axis, either diagonal or horizontal/vertical, or 
	both like what we do below */
	
import java.util.Arrays;
import java.lang.Math;

public class Main
{
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return;
        }
        final int mx = matrix.length;
        final int my = matrix[0].length;
        int x, y;
        int t;
        
        /* first we do the flip along the diagonal line from top right to bottom left */
        int _my = my - 1; // this variable is for the number of element on each row (num of columns), which should be one fewer then the row above
        for (x = 0; x < mx - 1; x++) {
            for (y = 0; y < _my; y++) {
                // when swapping, the x,y should be y,x in the flipping
                int ny = mx - 1 - x; //
                int nx = my - 1 - y;
                t = matrix[y][x];
                matrix[y][x] = matrix[ny][nx];
                matrix[ny][nx] = t;
            }
            _my--; // reduce the number of columns as we move row by row from the top
        }
        
        /* then we will do the flip/reverse for each column */
        for (x=0; x<mx/2; x++) 
            for (y=0; y<my; y++) {
                int nx = mx -1 - x; 
                int ny = y;
                t = matrix[x][y];
                matrix[x][y] = matrix[nx][ny];
                matrix[nx][ny] = t;
            }
        }
        for (int i=0; i<matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
	public static void main(String[] args) {
	    Main test = new Main();
	    test.rotate(new int[][]{{1,2,4,7},{5,6,9,11},{3,12,4,7},{5,3,8,6}});
	}
	
}
