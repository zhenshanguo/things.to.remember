I was searching for proper answer got explanation in the below link. I just added code and improved on explanation
courtesy : https://www.lintcode.com/problem/permutation-sequence/note/187602
Since we need to fix one position each time me need next we need to get permutations for (n-1)!

when n = 4, the list is 1, 2, 3, 4 and k = 18
the total number of permutations is
row1: 1 + {2, 3, 4}: 1234, 1243, 1324, 1342, 1423, 1432
==> 3! = 6 (factor) (from 0 to 5)
row2: 2 + {1, 3, 4}: 2134, 2143, 2314, 2341, 2413, 2431
==> 3! = 6 (from 6 to 11)
row3: 3 + {1, 2, 4}: 3! (from 12 to 17)
row4: 4 + {1, 2, 3}: 3! (from 18 to 23)
when k = 18, it means find the item from 0, then 17th is the permutation we want.

Since K =18 means we need to go to 17th index
so
K--

Find out the number on each position
while i = n = 4
list: 1,2,3,4
index = (k - 1)/ (i - 1)! = 17/6 = 2. index 2 means it is row3 beginning with 3, then remove 3 from the list {1,2,3,4}, then the list becomes {1,2,4}. update k = (k-1) % 6 = 5.**** Add 3 to output.****

When n = 3
row 1
1,2,4
1,4,2

row 2
2,1,4
2,4,1

row 3
4,1,2
4,2,1

while i = n = 3, k = 5
list: 1, 2, 4
index = k / (i - 1)! = 5/2 = 2. index 2 means it is row3 beginning with 4, then remove 4 from the list {1, 2, 4}, then the list becomes {1, 4}. update k = k % 2 = 1. **** Add 4 to output.****

When n = 2
1,2

while i = n = 2, k = 1
list: 1,2
index = k/(i-1)! = 1/1 = 1. index 1 means it is row2 beginning with 2, then remove 2 from the list {1, 2}, then the list becomes {1}. update k = k % 1 = 0. Add 2 to the output.

When n = 1

1
while i = n = 1, k = 0
index = 0, index 0 means it is row1 beginning with 1. Add 1 to the output

Eventually, the output is 3421

Why to Divide by Factorial ?

Think about a simple case where you are finding the digits of a decimal number e.g. 4836. You can compute rightmost place by dividing 4836 by 1 then modulo 10 to get 6. Then 4836 by 10 modulo 10 to get 3, then 4836 by 100 modulo 10 to get 8 etc. We don't need to change the number 4836 each time. Permutation case is similar but we use factorials instead of powers of 10 and modulus value changes.

https://stackoverflow.com/questions/31216097/given-n-and-k-return-the-kth-permutation-sequence

private String getPermutation3(int n, int k) {

    List<Integer> list = new ArrayList<>();

    for(int i =1; i <= n ; i++){
        list.add(i);
    }

    int[] fact = new int[n];

   // Since we cannot divide a number by 0 we are fixing index 0 to 1
    fact[0] =1;

    // Storing factorials for (n-1)!

    for(int i = 1; i < n; i++){
        fact[i] = i*fact[i-1];
    }
    // if position is 3 we need index 2 so k--
   k--;

    String s = "";

    // We are coming from back as we need to get the position of character for (n-1)! and we have put factorial in
    // increasing order in our factorial array so to get last character we need to come from reverse

    for(int i = n-1; i >=0 ; i--){

        int index = k/fact[i];

        s = s+ list.remove(index);
// To go to next index
        k = k%fact[i];
    }
    return s;
}
