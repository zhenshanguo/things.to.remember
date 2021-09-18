Introduction
# Given the weights and profits of ‘N’ items, we are asked to put these items
in a knapsack which has a capacity ‘C’. The goal is to get the maximum profit out of the
items in the knapsack. Each item can only be selected once, as we don’t have multiple
quantities of any item.

Let’s take the example of Merry, who wants to carry some fruits in the knapsack to get
maximum profit. Here are the weights and profits of the fruits:

Items: { Apple, Orange, Banana, Melon } Weights: { 2, 3, 1, 4 } Profits: { 4, 5, 3, 7 }
Knapsack capacity: 5

Let’s try to put various combinations of fruits in the knapsack, such that their total
weight is not more than 5:

Apple + Orange (total weight 5) => 9 profit Apple + Banana (total weight 3) => 7 profit
Orange + Banana (total weight 4) => 8 profit Banana + Melon (total weight 5) => 10 profit

This shows that Banana + Melon is the best combination as it gives us the maximum profit
and the total weight does not exceed the capacity.

Problem Statement # Given two integer arrays to represent weights and profits of ‘N’
items, we need to find a subset of these items which will give us maximum profit such that
their cumulative weight is not more than a given number ‘C’. Each item can only be
selected once, which means either we put an item in the knapsack or we skip it.

Basic Solution # A basic brute-force solution could be to try all combinations of the
given items (as we did above), allowing us to choose the one with maximum profit and a
weight that doesn’t exceed ‘C’. Take the example of four items (A, B, C, and D), as shown
in the diagram below. To try all the combinations, our algorithm will look like:

12345 for each item 'i'   create a new set which INCLUDES item 'i' if the total weight
does not exceed the capacity, and      recursively process the remaining capacity and
items  create a new set WITHOUT item 'i', and recursively process the remaining items
return the set from the above two sets with higher profit

class Knapsack {

  public int solveKnapsack(int[] profits, int[] weights, int capacity) {
    return this.knapsackRecursive(profits, weights, capacity, 0);
  }

  private int knapsackRecursive(int[] profits, int[] weights, int capacity, int currentIndex) {
    // base checks
    if (capacity <= 0 || currentIndex >= profits.length)
      return 0;

    // recursive call after choosing the element at the currentIndex
    // if the weight of the element at currentIndex exceeds the capacity, we shouldn't process this
    int profit1 = 0;
    if( weights[currentIndex] <= capacity )
        profit1 = profits[currentIndex] + knapsackRecursive(profits, weights,
                capacity - weights[currentIndex], currentIndex + 1);

    // recursive call after excluding the element at the currentIndex
    int profit2 = knapsackRecursive(profits, weights, capacity, currentIndex + 1);

    return Math.max(profit1, profit2);
  }

  public static void main(String[] args) {
    Knapsack ks = new Knapsack();
    int[] profits = {1, 6, 10, 16};
    int[] weights = {1, 2, 3, 5};
    int maxProfit = ks.solveKnapsack(profits, weights, 7);
    System.out.println("Total knapsack profit ---> " + maxProfit);
    maxProfit = ks.solveKnapsack(profits, weights, 6);
    System.out.println("Total knapsack profit ---> " + maxProfit);
  }
}

Time and Space complexity #

The time complexity of the above algorithm is exponential O(2 n), where ‘n’ represents
the total number of items. This can also be confirmed from the above recursion tree. As we
can see, we will have a total of ‘31’ recursive calls – calculated through ( 2 n ) + ( 2 n
) − 1, which is asymptotically equivalent to O ( 2 n ).

The space complexity is O ( n ) O(n). This space will be used to store the recursion
stack. Since the recursive algorithm works in a depth-first fashion, which means that we
can’t have more than ‘n’ recursive calls on the call stack at any time.



Top-down Dynamic Programming with Memorization
# Memorization is when we store the results of all the previously solved sub-problems and return the results from memory if we
encounter a problem that has already been solved.

Since we have two changing values (capacity and currentIndex) in our recursive function
knapsackRecursive(), we can use a two-dimensional array to store the results of all the
solved sub-problems. As mentioned above, we need to store results for every sub-array
(i.e. for every possible index ‘i’) and for every possible capacity ‘c’

class Knapsack {

  public int solveKnapsack(int[] profits, int[] weights, int capacity) {
    Integer[][] dp = new Integer[profits.length][capacity + 1];
    return this.knapsackRecursive(dp, profits, weights, capacity, 0);
  }

  private int knapsackRecursive(Integer[][] dp, int[] profits, int[] weights, int capacity,
      int currentIndex) {

    // base checks
    if (capacity <= 0 || currentIndex >= profits.length)
      return 0;

    // if we have already solved a similar problem, return the result from memory
    if(dp[currentIndex][capacity] != null)
      return dp[currentIndex][capacity];

    // recursive call after choosing the element at the currentIndex
    // if the weight of the element at currentIndex exceeds the capacity, we shouldn't process this
    int profit1 = 0;
    if( weights[currentIndex] <= capacity )
        profit1 = profits[currentIndex] + knapsackRecursive(dp, profits, weights,
                capacity - weights[currentIndex], currentIndex + 1);

    // recursive call after excluding the element at the currentIndex
    int profit2 = knapsackRecursive(dp, profits, weights, capacity, currentIndex + 1);

    dp[currentIndex][capacity] = Math.max(profit1, profit2);
    return dp[currentIndex][capacity];
  }

  public static void main(String[] args) {
    Knapsack ks = new Knapsack();
    int[] profits = {1, 6, 10, 16};
    int[] weights = {1, 2, 3, 5};
    int maxProfit = ks.solveKnapsack(profits, weights, 7);
    System.out.println("Total knapsack profit ---> " + maxProfit);
    maxProfit = ks.solveKnapsack(profits, weights, 6);
    System.out.println("Total knapsack profit ---> " + maxProfit);
  }
}

Time and Space complexity #

Since our memoization array dp[profits.length][capacity+1] stores the results for all
subproblems, we can conclude that we will not have more than N ∗ C N∗C subproblems (where
‘N’ is the number of items and ‘C’ is the knapsack capacity). This means that our time
complexity will be O(N∗C).

The above algorithm will use O(N∗C) space for the memoization array. Other
than that we will use O(N) space for the recursion call-stack. So the total space
complexity will be O(N∗C+N), which is asymptotically equivalent to O(N∗C).


Bottom-up Dynamic Programming # 
Let’s try to populate our dp[][] array from the above solution by working in a bottom-up
fashion. Essentially, we want to find the maximum profit for every sub-array and for every
possible capacity. This means that dp[i][c] will represent the maximum knapsack profit for
capacity ‘c’ calculated from the first ‘i’ items.

So, for each item at index ‘i’ (0 <= i < items.length) and capacity ‘c’ (0 <= c <=
capacity), we have two options:

Exclude the item at index ‘i’. In this case, we will take whatever profit we get from the
sub-array excluding this item => dp[i-1][c] Include the item at index ‘i’ if its weight is
not more than the capacity. In this case, we include its profit plus whatever profit we
get from the remaining capacity and from remaining items => profit[i] +
dp[i-1][c-weight[i]] Finally, our optimal solution will be maximum of the above two
values:

    dp[i][c] = max (dp[i-1][c], profit[i] + dp[i-1][c-weight[i]])
    
class Knapsack {

  public int solveKnapsack(int[] profits, int[] weights, int capacity) {
    // basic checks
    if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
      return 0;

    int n = profits.length;
    int[][] dp = new int[n][capacity + 1];

    // populate the capacity=0 columns, with '0' capacity we have '0' profit
    for(int i=0; i < n; i++)
      dp[i][0] = 0;

    // if we have only one weight, we will take it if it is not more than the capacity
    for(int c=0; c <= capacity; c++) {
      if(weights[0] <= c)
        dp[0][c] = profits[0];
    }

    // process all sub-arrays for all the capacities
    for(int i=1; i < n; i++) {
      for(int c=1; c <= capacity; c++) {
        int profit1= 0, profit2 = 0;
        // include the item, if it is not more than the capacity
        if(weights[i] <= c)
          profit1 = profits[i] + dp[i-1][c-weights[i]];
        // exclude the item
        profit2 = dp[i-1][c];
        // take maximum
        dp[i][c] = Math.max(profit1, profit2);
      }
    }

    // maximum profit will be at the bottom-right corner.
    return dp[n-1][capacity];
  }

  public static void main(String[] args) {
    Knapsack ks = new Knapsack();
    int[] profits = {1, 6, 10, 16};
    int[] weights = {1, 2, 3, 5};
    int maxProfit = ks.solveKnapsack(profits, weights, 7);
    System.out.println("Total knapsack profit ---> " + maxProfit);
    maxProfit = ks.solveKnapsack(profits, weights, 6);
    System.out.println("Total knapsack profit ---> " + maxProfit);
  }
}

Time and Space complexity #

The above solution has the time and space complexity of O(N∗C), where ‘N’ represents total
items and ‘C’ is the maximum capacity.

How can we find the selected items? #

As we know, the final profit is at the bottom-right corner. Therefore, we will start from
there to find the items that will be going into the knapsack.

As you remember, at every step we had two options: include an item or skip it. If we skip
an item, we take the profit from the remaining items (i.e. from the cell right above it);
if we include the item, then we jump to the remaining profit to find more items.

import java.util.*;

class Knapsack {

  public int solveKnapsack(int[] profits, int[] weights, int capacity) {
    // base checks
    if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
      return 0;

    int n = profits.length;
    int[][] dp = new int[n][capacity + 1];

    // populate the capacity=0 columns, with '0' capacity we have '0' profit
    for(int i=0; i < n; i++)
      dp[i][0] = 0;

    // if we have only one weight, we will take it if it is not more than the capacity
    for(int c=0; c <= capacity; c++) {
      if(weights[0] <= c)
        dp[0][c] = profits[0];
    }

    // process all sub-arrays for all the capacities
    for(int i=1; i < n; i++) {
      for(int c=1; c <= capacity; c++) {
        int profit1= 0, profit2 = 0;
        // include the item, if it is not more than the capacity
        if(weights[i] <= c)
          profit1 = profits[i] + dp[i-1][c-weights[i]];
        // exclude the item
        profit2 = dp[i-1][c];
        // take maximum
        dp[i][c] = Math.max(profit1, profit2);
      }
    }

    printSelectedElements(dp, weights, profits, capacity);
    // maximum profit will be at the bottom-right corner.
    return dp[n-1][capacity];
  }

 private void printSelectedElements(int dp[][], int[] weights, int[] profits, int capacity){
   System.out.print("Selected weights:");
   int totalProfit = dp[weights.length-1][capacity];
   for(int i=weights.length-1; i > 0; i--) {
     if(totalProfit != dp[i-1][capacity]) {
       System.out.print(" " + weights[i]);
       capacity -= weights[i];
       totalProfit -= profits[i];
     }
   }

   if(totalProfit != 0)
     System.out.print(" " + weights[0]);
   System.out.println("");
 }

  public static void main(String[] args) {
    Knapsack ks = new Knapsack();
    int[] profits = {1, 6, 10, 16};
    int[] weights = {1, 2, 3, 5};
    int maxProfit = ks.solveKnapsack(profits, weights, 7);
    System.out.println("Total knapsack profit ---> " + maxProfit);
    maxProfit = ks.solveKnapsack(profits, weights, 6);
    System.out.println("Total knapsack profit ---> " + maxProfit);
  }
}

