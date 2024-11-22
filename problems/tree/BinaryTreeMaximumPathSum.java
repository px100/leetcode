
// https://leetcode.com/problems/binary-tree-maximum-path-sum/

public class BinaryTreeMaximumPathSum {

  int ans = Integer.MIN_VALUE;

  public int maxPathSum(TreeNode root) {
    if (root == null) {
      return 0;
    }

    helper(root);

    return ans;
  }

  private int helper(TreeNode node) {
    if (node == null) {
      return 0;
    }

    int left = Math.max(0, helper(node.left));
    int right = Math.max(0, helper(node.right));
    ans = Math.max(ans, node.val + left + right);

    return node.val + Math.max(left, right);
  }
}
