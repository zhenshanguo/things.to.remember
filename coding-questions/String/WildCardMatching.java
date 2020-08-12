/*
Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character. '*' Matches any sequence of characters (including the empty sequence).

The matching should cover the entire input string (not partial).

The function prototype should be:

bool isMatch(const char *s, const char *p)
Some examples:

isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "*") → true
isMatch("aa", "a*") → true
isMatch("ab", "?*") → true
isMatch("aab", "c*a*b") → false
分析
跟上一题很类似。

主要是'*'的匹配问题。p每遇到一个'*'，就保留住当前'*'的坐标和s的坐标，然后s从前往后扫描，如果不成功，则s++，重新扫描。
*/

// Wildcard Matching
// 递归版，会超时，用于帮助理解题意
// 时间复杂度O(n!*m!)，空间复杂度O(n)

class Solution {
    public boolean isMatch(String s, String p) {
        return isMatch(s, 0, p, 0);
    }
    private boolean isMatch(String s, int i, String p, int j) {
        if (i == s.length() && j == p.length()) return true;
        if (i == s.length() || j == p.length()) return false;

        if (p.charAt(j) == '*') {
            while (j < p.length() && p.charAt(j) == '*') ++j;  //skip continuous '*' and move to the char after '*'
            if (j == p.length()) return true;
            while (i < s.length() && !isMatch(s, i, p, j)) ++i;

			/* this is confusing, but it is related to the 'while' condition above
			   if i< s.length(), that means !isMatch(s, i, p, j) is false, and implies 
			   isMatch(s, i, p, j) is true. a easy to understand way is: 
			   while (i < s.length()) {
			   		if (isMatch(s,i,p,j) return true;
			   		else i++;
			   	}
			   	return false;
			*/
            return i < s.length();
        }
        else if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '?') 
            return isMatch(s, ++i, p, ++j);
        else return false;
    }
}

// Wildcard Matching
// 迭代版，时间复杂度O(n*m)，空间复杂度O(1)
public class Solution {
    public boolean isMatch(String s, String p) {
        int i = 0, j = 0;
        int ii = -1, jj = -1;
        while (i < s.length()) {
            if (j < p.length() && p.charAt(j) == '*') {
                // skip continuous '*'
                while (j < p.length() && p.charAt(j) == '*') ++j;
                if (j == p.length()) return true;
                ii = i;
                jj = j;
            }
            if (j < p.length() && (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i))) {
                ++i; ++j;
            } else {
                if (ii == -1) return false; // there is no '*' before current, that's why ii == -1
                ++ii; // move to next char and try to match
                i = ii;
                j = jj;
            }
        }
        // skip continuous '*'
        while (j < p.length() && p.charAt(j) == '*') ++j;
        return i == s.length() && j == p.length();
    }
}