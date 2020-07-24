/* Given numRows, generate the first numRows of Pascal's triangle.

For example, given numRows = 5,
Return

[
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]
*/

import java.util.*;
import java.lang.*;

public class Main
{
    public List<List<Integer>> generate(int numRows) {
        ArrayList<List<Integer>> rt = new ArrayList<List<Integer>>();
        Integer[] pre = null;
        for (int i = 1; i <= numRows; i++) {
            //must be defined as Integer[], so we can manipulate the numbers based on index
            Integer[] row = new Integer[i];
            
            /* initialize the first and last element to be 1 in each row */
            row[0] = 1;
            row[i - 1] = 1;
            
            /* each element at position i equals to the sum of number at i and number at i-1 in previous row */
            for (int j = 1; j < i - 1; j++) {
                row[j] = pre[j] + pre[j - 1];
            }
            rt.add(new ArrayList<Integer>(Arrays.asList(row)));
            pre = row;
        }
        return rt;
    }

	public static void main(String[] args) {
	    Main test = new Main();
	    List<List<Integer>> rt = test.generate(6);
	    System.out.println(rt);
	}
}