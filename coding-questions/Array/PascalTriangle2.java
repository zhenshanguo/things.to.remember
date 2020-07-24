/* Given an index k, return the kth row of the Pascal's triangle.

For example, given k = 3,
Return [1,3,3,1].
*/

import java.util.*;
import java.lang.*;

public class Main
{
    
    public List<Integer> gePascalRow(int rowIndex) {
        Integer[] row = new Integer[rowIndex + 1];
        
        /* fill all elements to be 1, so we can do in-place calculation */
        Arrays.fill(row, 1);
        
        /* start the calculation from row 3 in the triangle, there are rowIndex +1 rows, and minors 2, 
           so, there are rowIndex -1 rows to calculate. in row for i, there are i + 3 elements, so the second last
           element index should be i +3 -2 = i+1. The reason the calculation starts from the right is 
           to avoid overwriting elements to be used in next calculation. */
           
        for (int i = 0; i < rowIndex - 1; i++) {
            for (int j = i + 1; j >= 1; j--) {
                row[j] = row[j] + row[j - 1];
            }
        }
        return Arrays.asList(row);
    }

	public static void main(String[] args) {
	    Main test = new Main();
	    List<Integer> rt = test.gePascalRow(9);
	    System.out.println(rt);
	}
}