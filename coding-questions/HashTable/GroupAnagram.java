/*
Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:

For the return value, each inner list's elements must follow the lexicographic order.
All inputs will be in lower-case.
*/

/*
把每个string，按照字母分组，如”abc”和”cba”是一组
维护一个map，key是abc，value是abc一组string出现的下标
把每一组string找出，排序，加入结果
*/

import java.util.*;
import java.lang.*;

public class Main
{
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<List<String>>();
        }
        List<List<String>> rt = new ArrayList<List<String>>();
        Map<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
        // 把单词分组
        for (int i = 0; i < strs.length; i++) {
            char[] c = strs[i].toCharArray();
            Arrays.sort(c);
            String k = Arrays.toString(c);
            ArrayList<Integer> list = new ArrayList<Integer>();
            if (map.containsKey(k)) {
                list = map.get(k);
            }
            list.add(i);
            map.put(k, list);
        }
        for (String s : map.keySet()) {
            List<Integer> l = map.get(s);
            List<String> group = new ArrayList<String>();
            // 把相同字母的单词放入同一个list
            for (Integer i : l) {
                group.add(strs[i]);
            }
            // 按字典序排序
            group.sort(new Comparator<String>() {
                public int compare(String x, String y) {
                    return x.compareTo(y);
                }
            });
            rt.add(group);
        }
        return rt;
    }
    
	public static void main(String[] args) {
	    Main test = new Main();
	    String[] data = new String[] {"ate", "eat","tea","nat","tan","bat"};
	    List<List<String>> rt = test.groupAnagrams(data);
	    System.out.println(rt);
	}
}