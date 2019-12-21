
// https://leetcode.com/problems/count-complete-tree-nodes/

public class CountCompleteTreeNodes {

  public int countNodes(TreeNode root) {
    if (root == null) {
      return 0;
    }

    return 1 + countNodes(root.left) + countNodes(root.right);
  }

  public int countNodesFast(TreeNode root) {
    int leftDepth = leftDepth(root);
    int rightDepth = rightDepth(root);

    if (leftDepth == rightDepth) {
      return (1 << leftDepth) - 1;
    }

    return 1 + countNodesFast(root.left) + countNodesFast(root.right);
  }

  private int rightDepth(TreeNode root) {
    int depth = 0;
    while (root != null) {
      root = root.right;
      depth++;
    }

    return depth;
  }

  private int leftDepth(TreeNode root) {
    int depth = 0;
    while (root != null) {
      root = root.left;
      depth++;
    }

    return depth;
  }
}
