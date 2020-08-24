Tree Diameter (medium) #
Given a binary tree, find the length of its diameter. The diameter of a tree is the number
of nodes on the longest path between any two leaf nodes. The diameter of a tree may or may
not pass through the root.

Solution #
This problem follows the Binary Tree Path Sum pattern. We can follow the same DFS
approach. There will be a few differences:

At every step, we need to find the height of both children of the current node. For this,
we will make two recursive calls similar to DFS. The height of the current node will be
equal to the maximum of the heights of its left or right children, plus ‘1’ for the
current node. The tree diameter at the current node will be equal to the height of the
left child plus the height of the right child plus ‘1’ for the current node: diameter =
leftTreeHeight + rightTreeHeight + 1. To find the overall tree diameter, we will use a
class level variable. This variable will store the maximum diameter of all the nodes
visited so far, hence, eventually, it will have the final tree diameter.


class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
};

class TreeDiameter {

  private static int treeDiameter = 0;

  public static int findDiameter(TreeNode root) {
    calculateHight(root);
    return treeDiameter;
  }

  private static int calculateHight(TreeNode currentNode) {
    if (currentNode == null)
      return 0;

    int leftTreeHeight = calculateHight(currentNode.left);
    int rightTreeHeight = calculateHight(currentNode.right);

    // diameter at the current node will be equal to the height of left subtree +
    // the height of right sub-trees + '1' for the current node
    int diameter = leftTreeHeight + rightTreeHeight + 1;

    // update the global tree diameter
    treeDiameter = Math.max(treeDiameter, diameter);

    // height of the current node will be equal to the maximum of the hights of
    // left or right subtrees plus '1' for the current node
    return Math.max(leftTreeHeight, rightTreeHeight) + 1;
  }

  public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(3);
    root.left.left = new TreeNode(4);
    root.right.left = new TreeNode(5);
    root.right.right = new TreeNode(6);
    System.out.println("Tree Diameter: " + TreeDiameter.findDiameter(root));
    root.left.left = null;
    root.right.left.left = new TreeNode(7);
    root.right.left.right = new TreeNode(8);
    root.right.right.left = new TreeNode(9);
    root.right.left.right.left = new TreeNode(10);
    root.right.right.left.left = new TreeNode(11);
    System.out.println("Tree Diameter: " + TreeDiameter.findDiameter(root));
  }
}


Time complexity #
The time complexity of the above algorithm is O(N), where ‘N’ is the total number of
nodes in the tree. This is due to the fact that we traverse each node once.

Space complexity #
The space complexity of the above algorithm will be O(N) in the worst case. This space
will be used to store the recursion stack. The worst case will happen when the given tree
is a linked list (i.e., every node has only one child).



