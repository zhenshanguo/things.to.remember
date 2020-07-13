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
        int _my = my - 1;
        for (x = 0; x < mx - 1; x++) {
            for (y = 0; y < _my; y++) {
                int ny = mx - 1 - x;
                int nx = my - 1 - y;
                t = matrix[y][x];
                matrix[y][x] = matrix[ny][nx];
                matrix[ny][nx] = t;
            }
            _my--;
        }
        for (x = 0; x < mx; x++) {
            for (y = 0; y < my / 2; y++) {
                int ny = my - 1 - y;
                int nx = x;
                t = matrix[y][x];
                matrix[y][x] = matrix[ny][nx];
                matrix[ny][nx] = t;
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
