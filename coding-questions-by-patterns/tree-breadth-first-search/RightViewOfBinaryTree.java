Right View of a Binary Tree (easy) #
Given a binary tree, return an array containing nodes in its right view. The right view of
a binary tree is the set of nodes visible when the tree is seen from the right side.

Solution #
This problem follows the Binary Tree Level Order Traversal pattern. We can follow the same
BFS approach. The only additional thing we will be do is to append the last node of each
level to the result array.

import java.util.*;

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
};

class RightViewTree {
  public static List<TreeNode> traverse(TreeNode root) {
    List<TreeNode> result = new ArrayList<>();
    if (root == null)
      return result;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int levelSize = queue.size();
      for (int i = 0; i < levelSize; i++) {
        TreeNode currentNode = queue.poll();
        // if it is the last node of this level, add it to the result
        if (i == levelSize - 1)
          result.add(currentNode);
        // insert the children of current node in the queue
        if (currentNode.left != null)
          queue.offer(currentNode.left);
        if (currentNode.right != null)
          queue.offer(currentNode.right);
      }
    }

    return result;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(12);
    root.left = new TreeNode(7);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(9);
    root.right.left = new TreeNode(10);
    root.right.right = new TreeNode(5);
    root.left.left.left = new TreeNode(3);
    List<TreeNode> result = RightViewTree.traverse(root);
    for (TreeNode node : result) {
      System.out.print(node.val + " ");
    }
  }
}


Time complexity #
The time complexity of the above algorithm is O(N), where ‘N’ is the total number of
nodes in the tree. This is due to the fact that we traverse each node once.

Space complexity #
The space complexity of the above algorithm will be O(N)) as we need to return a list
containing the level order traversal. We will also need O(N) space for the queue.
Since we can have a maximum of N/2 nodes at any level (this could happen only at the
lowest level), therefore we will need O(N) space to store them in the queue.

Similar Questions #
Problem 1: Given a binary tree, return an array containing nodes in its left view. The
left view of a binary tree is the set of nodes visible when the tree is seen from the left
side.

Solution: 
We will be following a similar approach, but instead of appending the last element of each
level we will be appending the first element of each level to the output array.
