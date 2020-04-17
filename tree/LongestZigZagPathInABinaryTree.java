
// LC-1372
// https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/

public class LongestZigZagPathInABinaryTree {

  private int max = 0;

  public int longestZigZag(TreeNode root) {
    helper(root, 0, 0);
    return max;
  }

  private void helper(TreeNode root, int l, int r) {
    if (root == null) {
      return;
    }
    max = Math.max(max, Math.max(l, r));
    helper(root.left, r + 1, 0);
    helper(root.right, 0, l + 1);
  }
}
