
// LC-687
// https://leetcode.com/problems/longest-univalue-path/

public class LongestUnivaluePath {

  private int max = 0;

  public int longestUnivaluePath(TreeNode root) {
    if (root == null) {
      return max;
    }

    helper(root, root.val);

    return max;
  }

  private int helper(TreeNode root, int val) {
    if (root == null) {
      return 0;
    }

    int l = helper(root.left, root.val);
    int r = helper(root.right, root.val);
    max = Math.max(max, l + r);

    if (root.val == val) {
      return Math.max(l, r) + 1;
    }

    return 0;
  }
}
