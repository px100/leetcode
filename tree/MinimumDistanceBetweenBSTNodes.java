
// LC-783
// https://leetcode.com/problems/minimum-distance-between-bst-nodes/

public class MinimumDistanceBetweenBSTNodes {

  private int pre = Integer.MIN_VALUE >> 1;
  private int result = Integer.MAX_VALUE;

  public int minDiffInBST(TreeNode root) {
    helper(root);

    return result;
  }

  public void helper(TreeNode root) {
    if (root == null) {
      return;
    }

    helper(root.left);
    result = Math.min(result, root.val - pre);
    pre = root.val;
    helper(root.right);
  }
}
