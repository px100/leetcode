
// https://leetcode.com/problems/largest-bst-subtree

public class LargestBSTSubtree {

  private static class Node {

    int size = 0;
    int lower = Integer.MAX_VALUE;
    int upper = Integer.MIN_VALUE;
    boolean bst = false;
  }

  private int max = 0;

  public int largestBSTSubtree(TreeNode root) {
    if (root == null) {
      return 0;
    }

    largestBSTHelper(root);

    return max;
  }

  private Node largestBSTHelper(TreeNode root) {
    Node currNode = new Node();
    if (root == null) {
      currNode.bst = true;
      return currNode;
    }

    Node left = largestBSTHelper(root.left);
    Node right = largestBSTHelper(root.right);

    currNode.lower = Math.min(root.val, Math.min(left.lower, right.lower));
    currNode.upper = Math.max(root.val, Math.max(left.upper, right.upper));

    if (left.bst && root.val > left.upper && right.bst && root.val < right.lower) {
      currNode.size = left.size + right.size + 1;
      currNode.bst = true;
      max = Math.max(max, currNode.size);
    } else {
      currNode.size = 0;
    }

    return currNode;
  }
}
