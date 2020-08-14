
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static List<String> textJustify(String input, int L) {
      /* Enter your code here. */
      if (input == null || input.length() == 0) {
          return new ArrayList<String>();
      }
      
      List<String> result = new ArrayList<>();
      String[] words = input.split(" ");
      System.out.println(Arrays.toString(words));
      if (words.length == 1) {
          result.add(words[0]);
      }
      
      int begin = 0, len = 0;
      for (int i = 0; i< words.length; i++) {
          if (len + words[i].length() + i - begin > L) {
            result.add(processLine(words, begin, i-1, len, L, false));
            len = 0;
            begin = i;
          }
          len += words[i].length();
      }
      result.add(processLine(words, begin, words.length - 1, len, L, true));
      System.out.println(result);
      return result;
    }
    
    public static String processLine(String[] words, int begin, int end, int len, int lineLen, boolean isLastLine) {
        // add words from begin to i - 1 to current line
        StringBuilder sb = new StringBuilder();
        int numOfWords = end - begin +1;
        int numOfSpaces = lineLen - len;
        int numOfGaps = end - begin;
        //System.out.println(begin + " " + numOfWords + " " + numOfGaps + " " + numOfSpaces);
        for (int i = 0; i< numOfWords; i++) {
            sb.append(words[begin + i]);
            if (numOfGaps > 0) {
                int spaces = isLastLine? 1: (numOfSpaces/numOfGaps + (i < numOfSpaces%numOfGaps? 1: 0));
                // System.out.println(spaces);
                while (spaces > 0) {
                    sb.append(" ");
                    spaces--;
                }
            }
        }
        return sb.toString();
    }
  
    public static void main(String args[] ) throws Exception {
        String input = "Coursera provides universal access to the world's best education, partnering with top universities and organizations to offer courses online.";
        int L = 30;
      
        // System.out.println(textJustify(input, L));
        textJustify(input, L);
        
        /** DESIRED OUTPUT:
            Coursera   provides  universal
            access  to  the  world's  best
            education, partnering with top
            universities and organizations
            to offer courses online.
        */
    }
}
Coursera   provides  universal