import java.io.*;
import java.util.*;

class MyCode {
  public static boolean pathExists(int[][] in) {
    Map<Integer, List<Integer>> map = new HashMap<>();
    for (int[] t : in) {
      if (map.containsKey(t[1])) {
        List<Integer> list = new ArrayList<Integer>();
        map.put(t[1], list);
      }
      map.get(t[1])).add(t[0]);
    }
    
    for (Map.Entry<Integer, List<Integer>> entry: map) {
      if (!depChecking(entry.key(), map, bucketVisited)) {
        return false;
      }
    }
    pret 
  }
  
  public static boolean depChecking(Integer bucket, Map<Integer, List<Integer>> map, Set<Integer> visited) {
      Set<Integer> bucketVisited = new Hashset<>();
      List<Integer> deps = map.get(bucket);
      for (Integer i : deps) {
        if (visited.contains(i)) {
          return false;
        } else {
          visited.add(i);
          return depChecking(i, map, visited);
        }
      }
  }
  
	public static void main (String[] args) {
		System.out.println("Hello Java");
	}
}



// Your last C/C++ code is saved below:
// #include <iostream>
// using namespace std;

// int main() {
// 	cout<<"Hello";
// 	return 0;
// }
[[0,1], [1,2], [2,0]]
[[0,1], [2, 0], [3, 2], [5, 0], [3, 4]]
True [5, 4, 3, 2, 0, 1]