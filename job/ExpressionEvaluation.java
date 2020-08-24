/*
You are building an educational website and want to create a simple calculator for students to use. The calculator will only allow addition and subtraction of non-negative integers.

We also want to allow parentheses in our input. Given an expression string using the "+", "-", "(", and ")" operators like "5+(16-2)", write a function to parse the string and evaluate the result.

Sample output:
  calculate("5+16-((9-6)-(4-2))+1") => 21
  calculate("22+(2-4)") => 20
  calculate("6+9-12") => 3
  calculate("((1024))") => 1024
  calculate("1+(2+3)-(4-5)+6") => 13
  calculate("255") => 255

n: length of the input string


*/
import java.io.*;
import java.util.*;

public class Solution {
  
  public static int calculate(String input) {
    int start = 0; 
    int res = 0;
    int first = 0;
    while (start< input.length() -1 && input.charAt(start+1) != '+' && input.charAt(start+1) != '-') {
      start++;
    }
    first = Integer.parseInt(input.substring(0, start+1));
    
    for (int i=start; i<input.length(); i++) {
      if (input.charAt(i) == '+' || input.charAt(i) == '-') {
        boolean isPlus = input.charAt(i) == '+';
        start = i+1; 
        while (i< input.length() -1 && input.charAt(i+1) != '+' && input.charAt(i+1) != '-') {
          i++;
        }
        int second = Integer.parseInt(input.substring(start, i+1));
        if (isPlus) {
          res = first + second;
        } else {
          res = first - second;
        }
        first = res;
      }
    }
    return first;
  }
  public static void main(String[] argv) {
//     String expression1 = "6+9-12"; // = 3
//     System.out.println(calculate(expression1));
//     String expression2 = "1+2-3+4-5+6-7"; // = -2
//     System.out.println(calculate(expression2));
//     String expression3 = "100+200+300"; // = 600
//     System.out.println(calculate(expression3));
//     String expression4 = "1-2-3-0"; // = -4
//     System.out.println(calculate(expression4));
//     String expression5 = "255"; // = 255
//     System.out.println(calculate(expression5));
    
    String expression2_1 = "5+16-((9-6)-(4-2))+1";
    String expression2_2 = "22+(2-4)";
    String expression2_3 = "6+9-12";
    String expression2_4 = "((1024))";
    String expression2_5 = "1+(2+3)-(4-5)+6";
    String expression2_6 = "255";


  }
}


import java.io.*;
import java.util.*;

class Main {
    public static int calculate(String s) {

        Stack<Integer> stack = new Stack<Integer>();
        int operand = 0;
        int result = 0; // For the on-going result
        int sign = 1;  // 1 means positive, -1 means negative

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {

                // Forming operand, since it could be more than one digit
                operand = 10 * operand + (int) (ch - '0');

            } else if (ch == '+') {

                // Evaluate the expression to the left,
                // with result, sign, operand
                result += sign * operand;

                // Save the recently encountered '+' sign
                sign = 1;

                // Reset operand
                operand = 0;

            } else if (ch == '-') {

                result += sign * operand;
                sign = -1;
                operand = 0;

            } else if (ch == '(') {

                // Push the result and sign on to the stack, for later
                // We push the result first, then sign
                stack.push(result);
                stack.push(sign);

                // Reset operand and result, as if new evaluation begins for the new sub-expression
                sign = 1;
                result = 0;

            } else if (ch == ')') {

                // Evaluate the expression to the left
                // with result, sign and operand
                result += sign * operand;

                // ')' marks end of expression within a set of parenthesis
                // Its result is multiplied with sign on top of stack
                // as stack.pop() is the sign before the parenthesis
                result *= stack.pop();

                // Then add to the next operand on the top.
                // as stack.pop() is the result calculated before this parenthesis
                // (operand on stack) + (sign on stack * (result from parenthesis))
                result += stack.pop();

                // Reset the operand
                operand = 0;
            }
        }
        return result + (sign * operand);
    }
  public static void main(String[] argv) {
//     String expression1 = "6+9-12"; // = 3
//     System.out.println(calculate(expression1));
//     String expression2 = "1+2-3+4-5+6-7"; // = -2
//     System.out.println(calculate(expression2));
//     String expression3 = "100+200+300"; // = 600
//     System.out.println(calculate(expression3));
//     String expression4 = "1-2-3-0"; // = -4
//     System.out.println(calculate(expression4));
//     String expression5 = "255"; // = 255
//     System.out.println(calculate(expression5));
    
    String expression2_1 = "5+16-((9-6)-(4-2))+1";
    String expression2_2 = "22+((2-4))";
    System.out.println(calculate(expression2_2));
    String expression2_3 = "6+9-12";
    String expression2_4 = "((1024))";
    String expression2_5 = "1+(2+3)-(4-5)+6";
    String expression2_6 = "255";


  }
}

